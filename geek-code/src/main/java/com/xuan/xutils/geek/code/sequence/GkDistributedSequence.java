package com.xuan.xutils.geek.code.sequence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicLong;

import javax.sql.DataSource;

import javafx.util.Pair;

/**
 * 分布式序列号生成器
 *
 * @author xuan
 * @since 2018/1/10
 */
public class GkDistributedSequence {

    /**
     * 数据源
     */
    private DataSource dataSource;

    /**
     * 序列号名称
     */
    private String name;

    /**
     * 区间表名
     */
    private String tableName = "gk_distributed_sequence";

    /**
     * 获取区间失败重试次数
     */
    private int retryTimes;

    /**
     * 每次获取区间的步长
     */
    private int step;

    /**
     * 初始化区间起始位置，当区间不存在，需要初始化时，需要设置开始位置，开始位置为：从STEP_START+1
     */
    private long stepStart;

    /**
     * 区间缓存，每次获取一个区间，能分配多次序号
     * 其中Pair的key是区间的结束数值，value是当前分配序列号数值
     */
    private Pair<Long, AtomicLong> range;

    public GkDistributedSequence(DataSource dataSource, String name) {
        this(dataSource, name, 100, 1000, 0);
    }

    public GkDistributedSequence(DataSource dataSource, String name, int retryTimes, int step, int stepStart) {
        this.dataSource = dataSource;
        if (null == dataSource) {
            throw new RuntimeException("dataSource cannot be null");
        }

        this.name = name;
        if (null == name || name.trim().length() == 0) {
            throw new RuntimeException("name cannot be empty");
        }

        this.retryTimes = retryTimes;
        if (retryTimes < 1) {
            throw new RuntimeException("retryTimes cannot be less than 1");
        }

        this.step = step;
        if (step < 1) {
            throw new RuntimeException("step cannot be less than 1");
        }

        this.stepStart = stepStart;

        if (stepStart < 0) {
            throw new RuntimeException("stepStart cannot be less than 0");
        }
    }

    /**
     * 初始化
     *
     * @throws SQLException exception
     */
    public void init() throws SQLException {
        createTable();
        insertRange();
    }

    /**
     * 生成一个分布式序列号
     *
     * @return 序号
     */
    public synchronized long nextValue() throws SQLException {

        //如果缓存区间不存在，就从数据源中获取一个
        if (null == range) {
            nextRange();
        }

        //区间范围内获取序号并自增，如果超出区间范围返回-1
        long value = getAndIncrement();
        if (-1 == value) {
            nextRange();
            value = getAndIncrement();
        }
        return value;
    }

    private void nextRange() throws SQLException {

        for (int i = 0; i < retryTimes; i++) {
            //1、先获取数据源中目前的区间值
            long oldValue = selectRange();

            //2、获取新区间值
            long newValue = oldValue + step;

            //3、更新回数据源，这里会有乐观锁的操作，如果失败，会进行重试，只到成功或者超过重试次数
            if (updateRange(newValue, oldValue)) {
                range = new Pair<>(newValue, new AtomicLong(oldValue + 1));
                return;
            }
        }

        throw new RuntimeException("Retried too many times, retryTimes = " + retryTimes);
    }

    private long getAndIncrement() {
        Long rangeMax = range.getKey();
        AtomicLong currentNo = range.getValue();

        long currentNoLong = currentNo.getAndIncrement();
        if (currentNoLong > rangeMax) {
            return -1;
        }

        return currentNoLong;
    }

    private void createTable() throws SQLException {
        String sql = String.format(""
                + "CREATE TABLE IF NOT EXISTS %s("
                + "id           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',"
                + "name         varchar(128)        NOT NULL                COMMENT '区间名',"
                + "value        bigint(20)          NOT NULL                COMMENT '区间值',"
                + "modify_time  datetime            NOT NULL                COMMENT '最新修改值',"
                + "PRIMARY KEY (`id`),"
                + "UNIQUE `uk_name` (`name`)"
                + ")",
            tableName);

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

    private void insertRange() throws SQLException {

        String sql = String.format("INSERT IGNORE INTO %s(name,value,modify_time) VALUE(?,?,now())",
            tableName);

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setLong(2, stepStart);
            stmt.executeUpdate();
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    private boolean updateRange(Long newValue, Long oldValue) throws SQLException {

        String sql =
            String.format("UPDATE %s SET value=?,modify_time=now() WHERE name=? AND value=?",
                tableName);

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, newValue);
            stmt.setString(2, name);
            stmt.setLong(3, oldValue);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    private long selectRange() throws SQLException {
        String sql = String.format("SELECT value FROM %s WHERE name=?", tableName);

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long oldValue;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                String msg = String.format("Sequence range[%s] not exist, please check table[%s]", name, tableName);
                throw new RuntimeException(msg);
            }
            oldValue = rs.getLong(1);

            //一般不会发生，如果发生了说明long不在适合做序号存储字段了
            long delta = 100000000L;
            if (oldValue > Long.MAX_VALUE - delta) {
                String msg = String.format("Sequence range value[%s] overflow, please check table[%s]", oldValue,
                    tableName);
                throw new RuntimeException(msg);
            }

            return oldValue;
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
