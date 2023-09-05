package com.xuan.xutils.geek.code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.sql.DataSource;

import javafx.util.Pair;

/**
 * 分布式序号生成工具
 *
 * @author xuan
 * @since 2023/9/5
 */
public class GkDistributeSequence {

    /**
     * 获取区间失败重试次数
     */
    private final static int CONFIG_RETRY_TIMES = 10;

    /**
     * 每次获取区间的步长
     */
    private final static int CONFIG_STEP = 1000;

    /**
     * 初始化区间起始位置，当区间不存在，需要初始化时，需要设置开始位置，开始位置为：从STEP_START+1
     */
    private final static long CONFIG_STEP_START = 0;

    /**
     * 区间缓存，每次获取一个区间，能分配多次需要，这个MAP就是对区间的缓存
     * 其中Pair的key是区间的结束数值，value是当前分配序列号数值
     */
    private final static Map<String, Pair<Long, AtomicLong>> NAME_RANGE_CACHE = new HashMap<>();

    /**
     * 区间表名
     */
    private final static String TABLE_NAME = "gk_distribute_sequence";

    /**
     * 表是否初始化过
     */
    private static volatile boolean TABLE_INIT = false;

    /**
     * 生成一个分布式序列号（默认配置）
     *
     * @param dataSource 数据源
     * @param name       区间名称
     * @return 序号
     */
    public static Long getSequenceNo(DataSource dataSource, String name) {
        return getSequenceNo(dataSource, name, CONFIG_STEP_START, CONFIG_STEP, CONFIG_RETRY_TIMES);
    }

    /**
     * 生成一个分布式序列号（自定义配置）
     *
     * @param dataSource 数据源
     * @param name       区间名称
     * @param stepStart  区间起始位置
     * @param step       步长
     * @param retryTimes 失败重试次数
     * @return 序号
     */
    public synchronized static Long getSequenceNo(DataSource dataSource, String name, long stepStart, int step,
        int retryTimes) {
        return nextValue(dataSource, name, stepStart, step, retryTimes);
    }

    /**
     * 获取下一个序列号
     *
     * @param dataSource 数据源
     * @param name       序列号名称
     * @param stepStart  起始位置
     * @param step       步长
     * @param retryTimes 重试次数
     * @return 下一个序号
     */
    private static long nextValue(DataSource dataSource, String name, long stepStart, int step,
        int retryTimes) {

        //1、初始化数据源
        initRangeTable(dataSource);

        //2、尝试获取一个缓存区间
        Pair<Long, AtomicLong> range = NAME_RANGE_CACHE.get(name);

        //3、如果缓存区间不存在，就从数据源中获取一个
        if (null == range) {
            range = nextRange(dataSource, name, stepStart, step, retryTimes);
        }

        //4、区间范围内获取序号并自增，如果超出区间范围返回-1
        long value = getAndIncrement(range);
        if (-1 == value) {
            range = nextRange(dataSource, name, stepStart, step, retryTimes);
            value = getAndIncrement(range);
        }
        return value;
    }

    /**
     * 获取下一个区间
     *
     * @param dataSource 获取区间数据源
     * @param name       区间名
     * @param stepStart  区间起始位置（如果区间不存在，数据源就会创建一个，那么需要指定一个起始位置）
     * @param step       步长
     * @param retryTimes 重试次数
     * @return 返回区间，其中key是区间最后一个数值，value表示需要计数的当前数值
     */
    private static Pair<Long, AtomicLong> nextRange(DataSource dataSource, String name, long stepStart, int step,
        int retryTimes) {

        for (int i = 0; i < retryTimes; i++) {
            //1、先获取数据源中目前的区间值
            Long oldValue = selectRange(dataSource, name);

            //2、如果返回-1，表示区间不存在，需要进行初始化操作
            if (null == oldValue) {
                //初始化后重试
                insertRange(dataSource, name, stepStart);
                continue;
            }

            //3、获取新区间值
            long newValue = oldValue + step;

            //4、更新回数据源，这里会有乐观锁的操作，如果失败，会进行重试，只到成功或者超过重试次数
            if (updateRange(dataSource, newValue, oldValue, name)) {
                Pair<Long, AtomicLong> range = new Pair<>(newValue, new AtomicLong(oldValue + 1));
                NAME_RANGE_CACHE.put(name, range);
                return range;
            }
        }

        throw new RuntimeException("Retried too many times, retryTimes = " + retryTimes);
    }

    /**
     * 获取区间范围内下一个序号，如果超出范围，返回-1
     *
     * @param range 区间范围
     * @return 下一个序号
     */
    private static long getAndIncrement(Pair<Long, AtomicLong> range) {
        Long rangeMax = range.getKey();
        AtomicLong currentNo = range.getValue();

        long currentNoLong = currentNo.getAndIncrement();
        if (currentNoLong > rangeMax) {
            return -1;
        }

        return currentNoLong;
    }

    /**
     * 初始化区间存储表
     *
     * @param dataSource 数据源
     */
    private static void initRangeTable(DataSource dataSource) {
        if (TABLE_INIT) {
            return;
        }
        String sql = String.format(""
                + "CREATE TABLE IF NOT EXISTS %s("
                + "id           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',"
                + "name         varchar(128)        NOT NULL                COMMENT '区间名',"
                + "value        bigint(20)          NOT NULL                COMMENT '区间值',"
                + "modify_time  datetime            NOT NULL                COMMENT '最新修改值',"
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
            TABLE_INIT = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    /**
     * 插入一个新区间
     *
     * @param dataSource 数据源
     * @param name       区间名称
     * @param stepStart  区间启始位置
     */
    private static void insertRange(DataSource dataSource, String name, long stepStart) {

        String sql = String.format("INSERT IGNORE INTO %s(name,value,modify_time) VALUE(?,?,now())",
            TABLE_NAME);

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setLong(2, stepStart);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    /**
     * 更新区间值（传入新值和就值是为了所数据库的乐观锁）
     *
     * @param dataSource 数据源
     * @param newValue   新值
     * @param oldValue   旧值
     * @param name       区间名称
     * @return true/false
     */
    private static boolean updateRange(DataSource dataSource, Long newValue, Long oldValue, String name) {

        String sql =
            String.format("UPDATE %s SET value=?,modify_time=now() WHERE name=? AND value=?",
                TABLE_NAME);

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    /**
     * 查询区间值，如果区间不存在返回NULL
     *
     * @param dataSource 数据源
     * @param name       区间名称
     * @return 区间
     */
    private static Long selectRange(DataSource dataSource, String name) {

        String sql =
            String.format("SELECT value FROM %s WHERE name=?", TABLE_NAME);

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
                //没有区间数据，返回null
                return null;
            }
            oldValue = rs.getLong(1);

            //一般不会发生，如果发生了说明long不在适合做序号存储字段了
            long delta = 100000000L;
            if (oldValue > Long.MAX_VALUE - delta) {
                String msg = String.format("Sequence range value[%s] overflow, please check table[%s]", oldValue,
                    TABLE_NAME);
                throw new RuntimeException(msg);
            }

            return oldValue;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(rs);
            closeQuietly(stmt);
            closeQuietly(conn);
        }
    }

    /**
     * 关闭流，如果发送异常，默认忽略
     *
     * @param closeable 流
     */
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
