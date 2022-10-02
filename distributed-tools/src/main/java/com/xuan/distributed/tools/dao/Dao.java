package com.xuan.distributed.tools.dao;

/**
 * @author xuan
 * @since 2022/9/21
 */
public interface Dao {

    int update(String sql, Object... args);

    <T> T query(String sql, ResultSetExtractor<T> dataExtract, Object... args);
}
