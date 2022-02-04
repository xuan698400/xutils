package com.xuan.lock.db;

import javax.sql.DataSource;

import com.xuan.lock.Lock;

/**
 * 使用DB数据库来实现锁
 *
 * @author xuan
 * @since 2022/1/27
 */
public interface DbLock extends Lock {

    /**
     * 初始化锁的数据源
     *
     * @param dataSource 数据源
     */
    void init(DataSource dataSource);
}
