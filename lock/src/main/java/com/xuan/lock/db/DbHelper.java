package com.xuan.lock.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mysql.jdbc.MysqlErrorNumbers;
import com.xuan.lock.common.DbLockModel;

/**
 * @author xuan
 * @date 2018/4/29
 */
public class DbHelper {

    private final static String LOCK_TABLE_NAME = "xutils_lock";

    private final static String SQL_CREATE_TABLE = String.format(""
            + "CREATE TABLE IF NOT EXISTS %s("
            + "name varchar(32) NOT NULL,"
            + "value varchar(128) NOT NULL,"
            + "timeout int NOT NULL,"
            + "create_time bigint(20) NOT NULL,"
            + "PRIMARY KEY (`name`)"
            + ")",
        LOCK_TABLE_NAME);

    private final static String SQL_INSERT_LOCK = String.format(
        "INSERT INTO %s(name,value,timeout,create_time) VALUE(?,?,?,?)",
        LOCK_TABLE_NAME);

    private final static String SQL_DELETE_LOCK = String.format("DELETE FROM %s WHERE name=? AND create_time=?",
        LOCK_TABLE_NAME);

    private final static String SQL_DELETE_LOCK_UNCHECK = String.format("DELETE FROM %s WHERE name=?",
        LOCK_TABLE_NAME);

    private final static String SQL_SELECT_LOCK = String.format(
        "SELECT name,value,timeout,create_time FROM %s WHERE name=?",
        LOCK_TABLE_NAME);

    private final static String SQL_UPDATE_LOCK = String.format(
        "UPDATE %s SET value=?,timeout=? WHERE name=? AND create_time=?",
        LOCK_TABLE_NAME);

    public static void createTable(DataSource dataSource) {

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(SQL_CREATE_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    public static boolean insertLock(DataSource dataSource, String name, String value, Integer timeout) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_LOCK);
            stmt.setString(1, name);
            stmt.setString(2, value);
            stmt.setInt(3, timeout);
            stmt.setLong(4, System.currentTimeMillis());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            if (MysqlErrorNumbers.ER_DUP_ENTRY == e.getErrorCode()) {
                //主键冲突，表示获取锁失败
                return false;
            }
            throw new RuntimeException(e);
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    public static boolean updateLock(DataSource dataSource, String name, String value, Long createTime,
        Integer timeout) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_LOCK);
            stmt.setString(1, value);
            stmt.setInt(2, timeout);
            stmt.setString(3, name);
            stmt.setLong(4, createTime);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    public static boolean deleteLock(DataSource dataSource, String name, Long createTime) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_LOCK);
            stmt.setString(1, name);
            stmt.setLong(2, createTime);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    public static boolean deleteLock(DataSource dataSource, String name) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_LOCK_UNCHECK);
            stmt.setString(1, name);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    static DbLockModel selectLock(DataSource dataSource, String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_LOCK);
            stmt.setString(1, name);

            rs = stmt.executeQuery();
            if (!rs.next()) {
                return null;
            }

            DbLockModel lockModel = new DbLockModel();
            lockModel.setName(rs.getString(1));
            lockModel.setValue(rs.getString(2));
            lockModel.setTimeout(rs.getInt(3));
            lockModel.setCreateTime(rs.getLong(4));
            return lockModel;
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
