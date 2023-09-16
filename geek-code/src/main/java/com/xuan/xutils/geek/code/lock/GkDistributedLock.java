package com.xuan.xutils.geek.code.lock;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * 可重入分布式锁
 *
 * @author xuan
 * @since 2023/9/7
 */
public class GkDistributedLock {

    /**
     * 可重入分布式锁表
     */
    private final static String TABLE_NAME = "gk_distributed_reentrant_lock";

    /**
     * 数据源
     */
    private DataSource dataSource;

    /**
     * 锁名称
     */
    private String name;

    public GkDistributedLock(DataSource dataSource, String name) throws SQLException {
        this.dataSource = dataSource;
        this.name = name;
    }

    /**
     * 初始化
     *
     * @throws SQLException exception
     */
    public void init() throws SQLException {
        createTable();
        insertLock();
    }

    /**
     * 获取锁，默认可重入
     *
     * @param timeoutSecond 锁超时时间，单位：秒
     * @throws SQLException exception
     */
    public boolean tryLock(int timeoutSecond) throws SQLException {
        return tryLock(timeoutSecond, true);
    }

    /**
     * 释放锁，默认不判断持有者
     *
     * @return true/false
     * @throws SQLException exception
     */
    public boolean unLock() throws SQLException {
        return unLock(false);
    }

    /**
     * 获取锁
     *
     * @param timeoutSecond 锁超时时间，单位：秒
     * @param isReentrant   是否可重入，如果true表示可重入
     * @throws SQLException exception
     */
    public boolean tryLock(int timeoutSecond, boolean isReentrant) throws SQLException {
        if (isReentrant) {
            return updateByHolder(buildHolder(), buildExpireTime(timeoutSecond));
        } else {
            return update(buildHolder(), buildExpireTime(timeoutSecond));
        }
    }

    /**
     * 释放锁
     *
     * @param isCheckHolder 是否判断锁的拥有者，如果true表示只有拥有着才能释放锁
     * @return true/false
     * @throws SQLException exception
     */
    public boolean unLock(boolean isCheckHolder) throws SQLException {
        if (isCheckHolder) {
            return releaseByHolder(buildHolder());
        } else {
            return release();
        }
    }

    private String buildHolder() {
        try {
            return InetAddress.getLocalHost().getHostAddress() + ":" + Thread.currentThread().getName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private long buildExpireTime(int timeoutSecond) {
        return now() + ((long)timeoutSecond) * 1000;
    }

    private boolean releaseByHolder(String holder) throws SQLException {
        String sql = String.format(
            "UPDATE %s SET holder='', status=0, expire_time=? WHERE name=? AND holder=?",
            TABLE_NAME);

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, now());
            stmt.setString(2, name);
            stmt.setString(3, holder);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    private boolean release() throws SQLException {
        String sql = String.format(
            "UPDATE %s SET holder='', status=0, expire_time=? WHERE name=?",
            TABLE_NAME);

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, now());
            stmt.setString(2, name);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    private boolean updateByHolder(String holder, long expireTime)
        throws SQLException {

        String sql
            = String.format(
            "UPDATE %s SET holder=?, status=1, expire_time=?, cnt=cnt+1 WHERE name=? AND (holder=? OR status=0 OR "
                + "expire_time<?)", TABLE_NAME);

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, holder);
            stmt.setLong(2, expireTime);
            stmt.setString(3, name);
            stmt.setString(4, holder);
            stmt.setLong(5, now());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    private boolean update(String holder, long expireTime)
        throws SQLException {

        String sql
            = String.format(
            "UPDATE %s SET holder=?, status=1, expire_time=?, cnt=cnt+1 WHERE name=? AND (status=0 OR expire_time<?)",
            TABLE_NAME);

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, holder);
            stmt.setLong(2, expireTime);
            stmt.setString(3, name);
            stmt.setLong(4, now());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    private void insertLock() throws SQLException {

        String sql = String.format(
            "INSERT IGNORE INTO %s(name, holder, status, expire_time, cnt) VALUES (?, '', 0, ?, 0)", TABLE_NAME);

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setLong(2, now());
            stmt.executeUpdate();
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    private void createTable() throws SQLException {

        String sql = String.format(""
                + "CREATE TABLE IF NOT EXISTS %s("
                + "id           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',"
                + "name         varchar(128)        NOT NULL                COMMENT '名称',"
                + "holder       varchar(256)        NOT NULL                COMMENT '持有者',"
                + "status       tinyint(4)          NOT NULL                COMMENT '锁状态：0未持有、1持有',"
                + "expire_time  bigint(20)            NOT NULL              COMMENT '过期时间戳',"
                + "cnt          bigint(20)          NOT NULL                COMMENT '持有次数',"
                + "PRIMARY KEY (`id`),"
                + "UNIQUE `uk_name` (`name`)"
                + ")",
            TABLE_NAME);

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
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

    private long now() {
        return System.currentTimeMillis();
    }

}
