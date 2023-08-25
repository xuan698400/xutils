package com.xuan.moho.sql.executer.core;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import com.xuan.moho.sql.executer.ResultSetMapping;
import com.xuan.moho.sql.executer.SQLExecuter;

/**
 * @author xuan
 * @since 2022/9/9
 */
public class DefaultSQLExecuter implements SQLExecuter {

    private DataSource dataSource;

    public DefaultSQLExecuter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long insertBackId(String sql, Object... args) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setValues(ps, args);
            ps.executeUpdate();
            //获取自增ID
            long id = -1;
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } finally {
            closeQuietly(ps);
            closeQuietly(conn);
        }
    }

    @Override
    public int update(String sql, Object... args) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            setValues(ps, args);
            return ps.executeUpdate();
        } finally {
            closeQuietly(ps);
            closeQuietly(conn);
        }
    }

    @Override
    public <T> List<T> queryList(String sql, ResultSetMapping<T> resultSetMapping, Object... args) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            setValues(ps, args);
            rs = ps.executeQuery();

            List<T> dataList = new ArrayList<>();
            while (rs.next()) {
                T data = resultSetMapping.extract(rs);
                dataList.add(data);
            }
            return dataList;
        } finally {
            closeQuietly(rs);
            closeQuietly(ps);
            closeQuietly(conn);
        }
    }

    @Override
    public <T> T queryObject(String sql, ResultSetMapping<T> resultSetMapping, Object... args) throws SQLException {
        List<T> dataList = queryList(sql, resultSetMapping, args);
        return null != dataList && dataList.size() > 0 ? dataList.get(0) : null;
    }

    private void setValues(PreparedStatement ps, Object... args) throws SQLException {
        if (null == args) {
            return;
        }

        int paramIndex = 1;
        for (Object arg : args) {
            setValue(ps, paramIndex, arg);
            paramIndex++;
        }
    }

    private void setValue(PreparedStatement ps, int paramIndex, Object arg) throws SQLException {
        if (isStringValue(arg.getClass())) {
            ps.setString(paramIndex, arg.toString());
        } else if (isDateValue(arg.getClass())) {
            ps.setTimestamp(paramIndex, new java.sql.Timestamp(((java.util.Date)arg).getTime()));
        } else if (arg instanceof Calendar) {
            Calendar cal = (Calendar)arg;
            ps.setTimestamp(paramIndex, new java.sql.Timestamp(cal.getTime().getTime()), cal);
        } else {
            ps.setObject(paramIndex, arg);
        }
    }

    private boolean isStringValue(Class<?> argClazz) {
        return CharSequence.class.isAssignableFrom(argClazz) || StringWriter.class.isAssignableFrom(argClazz);
    }

    private boolean isDateValue(Class<?> argClazz) {
        return java.util.Date.class.isAssignableFrom(argClazz) &&
            !(java.sql.Date.class.isAssignableFrom(argClazz)
                || java.sql.Time.class.isAssignableFrom(argClazz)
                || java.sql.Timestamp.class.isAssignableFrom(argClazz));
    }

    private void closeQuietly(AutoCloseable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Throwable e) {
                //Ignore
            }
        }
    }

}
