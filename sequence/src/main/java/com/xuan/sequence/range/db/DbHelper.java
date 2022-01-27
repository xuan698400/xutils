package com.xuan.sequence.range.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.sql.DataSource;

import com.xuan.sequence.common.SeqException;

/**
 * 操作DB帮助类
 *
 * @author xuan
 * @date 2018/4/29
 */
class DbHelper {

    private static final long DELTA = 100000000L;

    private final static String SEQUENCE_TABLE_NAME = "xutils_sequence";

    private final static String SQL_CREATE_TABLE = String.format(""
            + "CREATE TABLE IF NOT EXISTS %s("
            + "name varchar(32) NOT NULL,"
            + "value varchar(128) NOT NULL,"
            + "modify_time DATETIME NOT NULL,"
            + "PRIMARY KEY (`name`)"
            + ")",
        SEQUENCE_TABLE_NAME);

    private final static String SQL_INSERT_RANGE =
        String.format("INSERT IGNORE INTO %s(name,value,modify_time) VALUE(?,?,?)",
            SEQUENCE_TABLE_NAME);

    private final static String SQL_UPDATE_RANGE =
        String.format("UPDATE %s SET value=?,modify_time=? WHERE name=? AND value=?",
            SEQUENCE_TABLE_NAME);

    private final static String SQL_SELECT_RANGE =
        String.format("SELECT value FROM %s WHERE name=?", SEQUENCE_TABLE_NAME);

    static void createTable(DataSource dataSource) {

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(SQL_CREATE_TABLE);
        } catch (SQLException e) {
            throw new SeqException(e);
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    private static void insertRange(DataSource dataSource, String name, long stepStart) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_RANGE);
            stmt.setString(1, name);
            stmt.setLong(2, stepStart);
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SeqException(e);
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    static boolean updateRange(DataSource dataSource, Long newValue, Long oldValue, String name) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_RANGE);
            stmt.setLong(1, newValue);
            stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            stmt.setString(3, name);
            stmt.setLong(4, oldValue);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new SeqException(e);
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    static Long selectRange(DataSource dataSource, String name, long stepStart) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long oldValue;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_RANGE);
            stmt.setString(1, name);

            rs = stmt.executeQuery();
            if (!rs.next()) {
                //没有此类型数据，需要初始化
                insertRange(dataSource, name, stepStart);
                return null;
            }
            oldValue = rs.getLong(1);

            if (oldValue < 0) {
                String msg =
                    "Sequence value cannot be less than zero, value = " + oldValue + ", please check table sequence"
                        + SEQUENCE_TABLE_NAME;
                throw new SeqException(msg);
            }

            if (oldValue > Long.MAX_VALUE - DELTA) {
                String msg =
                    "Sequence value overflow, value = " + oldValue + ", please check table sequence"
                        + SEQUENCE_TABLE_NAME;
                throw new SeqException(msg);
            }

            return oldValue;
        } catch (SQLException e) {
            throw new SeqException(e);
        } finally {
            closeQuietly(rs);
            closeQuietly(stmt);
            closeQuietly(conn);
        }
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
