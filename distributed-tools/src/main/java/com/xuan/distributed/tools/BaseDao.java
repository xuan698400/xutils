package com.xuan.distributed.tools;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.sql.DataSource;

import com.xuan.distributed.tools.sequence.SequenceException;

/**
 * @author xuan
 * @since 2022/9/9
 */
public abstract class BaseDao {
    private DataSource dataSource;

    public BaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected int update(String sql, Object... args) throws SequenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            setValues(ps, args);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new SequenceException(e);
        } finally {
            closeQuietly(ps);
            closeQuietly(conn);
        }
    }

    protected <T> T query(String sql, ResultSetExtractor<T> dataExtract, Object... args) throws SequenceException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            setValues(ps, args);
            rs = ps.executeQuery();
            return dataExtract.extract(rs);
        } catch (SQLException e) {
            throw new SequenceException(e);
        } finally {
            closeQuietly(rs);
            closeQuietly(ps);
            closeQuietly(conn);
        }
    }

    protected interface ResultSetExtractor<T> {
        T extract(ResultSet resultSet);
    }

    private void setValues(PreparedStatement ps, Object... args) throws SQLException {
        if (null != args) {
            int paramIndex = 1;
            for (Object arg : args) {
                setValue(ps, paramIndex, arg);
                paramIndex++;
            }
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
        return (CharSequence.class.isAssignableFrom(argClazz) ||
            StringWriter.class.isAssignableFrom(argClazz));
    }

    private boolean isDateValue(Class<?> argClazz) {
        return (java.util.Date.class.isAssignableFrom(argClazz) &&
            !(java.sql.Date.class.isAssignableFrom(argClazz) ||
                java.sql.Time.class.isAssignableFrom(argClazz) ||
                java.sql.Timestamp.class.isAssignableFrom(argClazz)));
    }

    private static void closeQuietly(AutoCloseable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Throwable e) {
                //Ignore
            }
        }
    }

}
