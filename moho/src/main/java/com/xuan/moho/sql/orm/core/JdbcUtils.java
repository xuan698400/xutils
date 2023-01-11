package com.xuan.moho.sql.orm.core;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

/**
 * @author xuan
 * @since 2023/1/11
 */
public class JdbcUtils {

    public static Object getResultSetValue(ResultSet rs, int index, Class<?> requiredType) throws SQLException {
        //if (requiredType == null) {
        //    return getResultSetValue(rs, index);
        //}
        //
        //Object value;
        //
        //// Explicitly extract typed value, as far as possible.
        //if (String.class == requiredType) {
        //    return rs.getString(index);
        //} else if (boolean.class == requiredType || Boolean.class == requiredType) {
        //    value = rs.getBoolean(index);
        //} else if (byte.class == requiredType || Byte.class == requiredType) {
        //    value = rs.getByte(index);
        //} else if (short.class == requiredType || Short.class == requiredType) {
        //    value = rs.getShort(index);
        //} else if (int.class == requiredType || Integer.class == requiredType) {
        //    value = rs.getInt(index);
        //} else if (long.class == requiredType || Long.class == requiredType) {
        //    value = rs.getLong(index);
        //} else if (float.class == requiredType || Float.class == requiredType) {
        //    value = rs.getFloat(index);
        //} else if (double.class == requiredType || Double.class == requiredType ||
        //    Number.class == requiredType) {
        //    value = rs.getDouble(index);
        //} else if (BigDecimal.class == requiredType) {
        //    return rs.getBigDecimal(index);
        //} else if (java.sql.Date.class == requiredType) {
        //    return rs.getDate(index);
        //} else if (java.sql.Time.class == requiredType) {
        //    return rs.getTime(index);
        //} else if (java.sql.Timestamp.class == requiredType || java.util.Date.class == requiredType) {
        //    return rs.getTimestamp(index);
        //} else if (byte[].class == requiredType) {
        //    return rs.getBytes(index);
        //} else if (Blob.class == requiredType) {
        //    return rs.getBlob(index);
        //} else if (Clob.class == requiredType) {
        //    return rs.getClob(index);
        //} else if (requiredType.isEnum()) {
        //    // Enums can either be represented through a String or an enum index value:
        //    // leave enum type conversion up to the caller (e.g. a ConversionService)
        //    // but make sure that we return nothing other than a String or an Integer.
        //    Object obj = rs.getObject(index);
        //    if (obj instanceof String) {
        //        return obj;
        //    } else if (obj instanceof Number) {
        //        // Defensively convert any Number to an Integer (as needed by our
        //        // ConversionService's IntegerToEnumConverterFactory) for use as index
        //        return NumberUtils.convertNumberToTargetClass((Number)obj, Integer.class);
        //    } else {
        //        // e.g. on Postgres: getObject returns a PGObject but we need a String
        //        return rs.getString(index);
        //    }
        //} else {
        //    // Some unknown type desired -> rely on getObject.
        //    if (getObjectWithTypeAvailable) {
        //        try {
        //            return rs.getObject(index, requiredType);
        //        } catch (AbstractMethodError err) {
        //            logger.debug("JDBC driver does not implement JDBC 4.1 'getObject(int, Class)' method", err);
        //        } catch (SQLFeatureNotSupportedException ex) {
        //            logger.debug("JDBC driver does not support JDBC 4.1 'getObject(int, Class)' method", ex);
        //        } catch (SQLException ex) {
        //            logger.debug("JDBC driver has limited support for JDBC 4.1 'getObject(int, Class)' method", ex);
        //        }
        //    }
        //
        //    // Corresponding SQL types for JSR-310 / Joda-Time types, left up
        //    // to the caller to convert them (e.g. through a ConversionService).
        //    String typeName = requiredType.getSimpleName();
        //    if ("LocalDate".equals(typeName)) {
        //        return rs.getDate(index);
        //    } else if ("LocalTime".equals(typeName)) {
        //        return rs.getTime(index);
        //    } else if ("LocalDateTime".equals(typeName)) {
        //        return rs.getTimestamp(index);
        //    }
        //
        //    // Fall back to getObject without type specification, again
        //    // left up to the caller to convert the value if necessary.
        //    return getResultSetValue(rs, index);
        //}
        //
        //// Perform was-null check if necessary (for results that the JDBC driver returns as primitives).
        //return (rs.wasNull() ? null : value);
        return null;
    }

    public static String underlineToCamel(String str) {
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

}
