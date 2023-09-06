package com.xuan.xutils.geek.code.sql;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

/**
 * SQL执行器
 *
 * @author xuan
 * @since 2023/9/6
 */
public class GkSqlExecuter {

    /**
     * 插入并返回自增的ID
     *
     * @param dataSource 数据源
     * @param sql        sql模版
     * @param args       参数
     * @return 自增ID
     * @throws SQLException exception
     */
    public static long insertBackId(DataSource dataSource, String sql, Object... args) throws SQLException {
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

    /**
     * 更新
     *
     * @param dataSource 数据源
     * @param sql        sql模版
     * @param args       参数
     * @return 成功条数
     * @throws SQLException exception
     */
    public static int update(DataSource dataSource, String sql, Object... args) throws SQLException {
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

    /**
     * 批量查询
     *
     * @param dataSource 数据源
     * @param sql        sql模版
     * @param args       参数
     * @return 查询结果
     * @throws SQLException exception
     */
    public static List<Map<String, Object>> queryList(DataSource dataSource, String sql, Object... args)
        throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            setValues(ps, args);
            rs = ps.executeQuery();

            List<Map<String, Object>> dataList = new ArrayList<>();

            while (rs.next()) {
                Map<String, Object> data = rsToMap(rs);
                dataList.add(data);
            }
            return dataList;
        } finally {
            closeQuietly(rs);
            closeQuietly(ps);
            closeQuietly(conn);
        }
    }

    /**
     * 单个查询
     *
     * @param dataSource 数据源
     * @param sql        sql模版
     * @param args       参数
     * @return 单条数据，注意如果查询多条，只会返回第一条
     * @throws SQLException exception
     */
    public static Map<String, Object> queryObject(DataSource dataSource, String sql, Object... args)
        throws SQLException {

        List<Map<String, Object>> dataList = queryList(dataSource, sql, args);
        return null != dataList && dataList.size() > 0 ? dataList.get(0) : null;
    }

    private static Map<String, Object> rsToMap(ResultSet rs) throws SQLException {
        ResultSetMetaData me = rs.getMetaData();
        int size = me.getColumnCount();
        Map<String, Object> dataMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            int index = i + 1;
            String columnName = me.getColumnName(index);
            Object value = rs.getObject(index);
            dataMap.put(columnName, value);
        }
        return dataMap;
    }

    private static void setValues(PreparedStatement ps, Object... args) throws SQLException {
        if (null == args) {
            return;
        }

        int paramIndex = 1;
        for (Object arg : args) {
            setValue(ps, paramIndex, arg);
            paramIndex++;
        }
    }

    private static void setValue(PreparedStatement ps, int paramIndex, Object arg) throws SQLException {
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

    private static boolean isStringValue(Class<?> argClazz) {
        return CharSequence.class.isAssignableFrom(argClazz) || StringWriter.class.isAssignableFrom(argClazz);
    }

    private static boolean isDateValue(Class<?> argClazz) {
        return java.util.Date.class.isAssignableFrom(argClazz) &&
            !(java.sql.Date.class.isAssignableFrom(argClazz)
                || java.sql.Time.class.isAssignableFrom(argClazz)
                || java.sql.Timestamp.class.isAssignableFrom(argClazz));
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
