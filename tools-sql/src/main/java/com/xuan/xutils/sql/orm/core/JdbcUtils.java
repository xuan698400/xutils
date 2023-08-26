package com.xuan.xutils.sql.orm.core;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xuan.xutils.base.exception.ExceptionFactory;

/**
 * @author xuan
 * @since 2023/1/11
 */
public class JdbcUtils {

    public static Object getResultSetValue(ResultSet rs, String columnName, Class<?> requiredType) throws SQLException {
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
            throw ExceptionFactory.bizException("数据模型类型不支持, requiredType:" + requiredType);
        }
        return value;
    }

}
