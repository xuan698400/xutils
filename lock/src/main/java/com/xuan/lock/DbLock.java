package com.xuan.lock;

import javax.sql.DataSource;

/**
 * @author xuan
 * @since 2022/1/27
 */
public interface DbLock extends Lock {
    void init(DataSource dataSource);
}
