package com.xuan.xutils.geek.code.sql;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
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

    private DataSource dataSource;

    public GkSqlExecuter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 插入并返回自增的ID
     *
     * @param sql  sql模版
     * @param args 参数
     * @return 自增ID
     * @throws SQLException exception
     */
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

    /**
     * 更新
     *
     * @param sql  sql模版
     * @param args 参数
     * @return 成功条数
     * @throws SQLException exception
     */
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

    /**
     * 批量查询
     *
     * @param sql  sql模版
     * @param args 参数
     * @return 查询结果
     * @throws SQLException exception
     */
    public List<Map<String, Object>> queryMapList(String sql, Object... args)
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
     * 批量查询
     *
     * @param beanClazz bean类型
     * @param sql       sql模版
     * @param args      参数
     * @param <T>       bean泛型
     * @return 查询结果
     * @throws SQLException exception
     */
    public <T> List<T> queryBeanList(Class<T> beanClazz, String sql, Object... args)
        throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            setValues(ps, args);
            rs = ps.executeQuery();

            List<T> beanList = new ArrayList<>();

            while (rs.next()) {
                T data = rsToBean(rs, beanClazz);
                beanList.add(data);
            }
            return beanList;
        } finally {
            closeQuietly(rs);
            closeQuietly(ps);
            closeQuietly(conn);
        }
    }

    /**
     * 单个查询
     *
     * @param sql  sql模版
     * @param args 参数
     * @return 单条数据，注意如果查询多条，只会返回第一条
     * @throws SQLException exception
     */
    public Map<String, Object> queryMap(String sql, Object... args)
        throws SQLException {

        List<Map<String, Object>> dataList = queryMapList(sql, args);
        return null != dataList && dataList.size() > 0 ? dataList.get(0) : null;
    }

    /**
     * 单个查询
     *
     * @param beanClazz bean类型
     * @param sql       sql模版
     * @param args      参数
     * @param <T>       泛型
     * @return 单条数据，注意如果查询多条，只会返回第一条
     * @throws SQLException exception
     */
    public <T> T queryBean(Class<T> beanClazz, String sql, Object... args)
        throws SQLException {

        List<T> dataList = queryBeanList(beanClazz, sql, args);
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

    private <T> T rsToBean(ResultSet rs, Class beanClass) throws SQLException {

        //创建实例对象
        Object bean;
        try {
            bean = beanClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        //遍历对象属性，从rs获取值
        Field[] fields = beanClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Class<?> fieldClazzType = field.getType();
            String columnName = camelToUnderline(fieldName);
            try {
                field.set(bean, getResultSetValue(rs, columnName, fieldClazzType));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return (T)bean;
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

    private String camelToUnderline(String str) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0, len = str.length(); i < len; i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                builder.append("_");
            }
            builder.append(Character.toLowerCase(c));
        }
        return builder.toString();
    }

    private Object getResultSetValue(ResultSet rs, String columnName, Class<?> requiredType) throws SQLException {
        Object value;

        if (String.class == requiredType) {
            return rs.getString(columnName);
        } else if (boolean.class == requiredType || Boolean.class == requiredType) {
            value = rs.getBoolean(columnName);
        } else if (byte.class == requiredType || Byte.class == requiredType) {
            value = rs.getByte(columnName);
        } else if (short.class == requiredType || Short.class == requiredType) {
            value = rs.getShort(columnName);
        } else if (int.class == requiredType || Integer.class == requiredType) {
            value = rs.getInt(columnName);
        } else if (long.class == requiredType || Long.class == requiredType) {
            value = rs.getLong(columnName);
        } else if (float.class == requiredType || Float.class == requiredType) {
            value = rs.getFloat(columnName);
        } else if (double.class == requiredType || Double.class == requiredType || Number.class == requiredType) {
            value = rs.getDouble(columnName);
        } else if (BigDecimal.class == requiredType) {
            return rs.getBigDecimal(columnName);
        } else if (java.sql.Date.class == requiredType) {
            return rs.getDate(columnName);
        } else if (java.sql.Time.class == requiredType) {
            return rs.getTime(columnName);
        } else if (java.sql.Timestamp.class == requiredType || java.util.Date.class == requiredType) {
            return rs.getTimestamp(columnName);
        } else if (byte[].class == requiredType) {
            return rs.getBytes(columnName);
        } else if (Blob.class == requiredType) {
            return rs.getBlob(columnName);
        } else if (Clob.class == requiredType) {
            return rs.getClob(columnName);
        } else {
            throw new RuntimeException("数据模型类型不支持, requiredType:" + requiredType);
        }
        return value;
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
