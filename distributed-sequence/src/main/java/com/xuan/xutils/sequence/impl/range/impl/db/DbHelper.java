package com.xuan.xutils.sequence.impl.range.impl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.xuan.xutils.sequence.SequenceException;

/**
 * 操作DB的工具类
 *
 * @author xuan
 * @date 2018/4/29
 */
class DbHelper {

    /**
     * 分配区间超过Long.MAX_VALUE之后，理论上就会出现问题，如果分配时超过Long.MAX_VALUE - DELTA时，我们提前预警报错
     */
    private static final long DELTA = 100000000L;

    /**
     * 区间分配表名
     */
    private final static String SEQUENCE_TABLE_NAME = "xutils_sequence_range";

    /**
     * 创建表SQL
     */
    private final static String SQL_CREATE_TABLE = String.format(""
            + "CREATE TABLE IF NOT EXISTS %s("
            + "id           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',"
            + "name         varchar(128)         NOT NULL                COMMENT '区间名',"
            + "value        bigint(20)          NOT NULL                COMMENT '区间值',"
            + "modify_time  datetime            NOT NULL                COMMENT '最新修改值',"
            + "PRIMARY KEY (`id`),"
            + "UNIQUE `uk_name` (`name`)"
            + ")",
        SEQUENCE_TABLE_NAME);

    /**
     * 插入SQL
     */
    private final static String SQL_INSERT_RANGE =
        String.format("INSERT IGNORE INTO %s(name,value,modify_time) VALUE(?,?,now())",
            SEQUENCE_TABLE_NAME);

    /**
     * 更新SQL
     */
    private final static String SQL_UPDATE_RANGE =
        String.format("UPDATE %s SET value=?,modify_time=now() WHERE name=? AND value=?",
            SEQUENCE_TABLE_NAME);

    /**
     * 查询SQL
     */
    private final static String SQL_SELECT_RANGE =
        String.format("SELECT value FROM %s WHERE name=?", SEQUENCE_TABLE_NAME);

    /**
     * 指定数据源，创建DB表
     *
     * @param dataSource 指定数据源
     */
    static void createTable(DataSource dataSource) {

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(SQL_CREATE_TABLE);
        } catch (SQLException e) {
            throw new SequenceException(e);
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    /**
     * 插入一条分配区间
     *
     * @param dataSource 数据源
     * @param name       区间名称
     * @param stepStart  启始位置
     */
    private static void insertRange(DataSource dataSource, String name, long stepStart) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_RANGE);
            stmt.setString(1, name);
            stmt.setLong(2, stepStart);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SequenceException(e);
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    /**
     * 更新区间
     *
     * @param dataSource 数据源
     * @param newValue   新值
     * @param oldValue   旧值
     * @param name       区间名称
     * @return true/false
     */
    static boolean updateRange(DataSource dataSource, Long newValue, Long oldValue, String name) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_RANGE);
            stmt.setLong(1, newValue);
            stmt.setString(2, name);
            stmt.setLong(3, oldValue);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new SequenceException(e);
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    /**
     * 查询区间
     *
     * @param dataSource 数据源
     * @param name       区间名称
     * @param stepStart  启始位置
     * @return 区间
     */
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
                String msg = String.format(
                    "Sequence range value[%s] cannot be less than zero, please check table sequence",
                    oldValue);
                throw new SequenceException(msg);
            }

            if (oldValue > Long.MAX_VALUE - DELTA) {
                String msg = String.format("Sequence range value[%s] overflow, please check table[%s]", oldValue,
                    SEQUENCE_TABLE_NAME);
                throw new SequenceException(msg);
            }

            return oldValue;
        } catch (SQLException e) {
            throw new SequenceException(e);
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
