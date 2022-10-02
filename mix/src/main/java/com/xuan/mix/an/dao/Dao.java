package com.xuan.mix.an.dao;

import java.sql.SQLException;

/**
 * @author xuan
 * @since 2022/9/21
 */
public interface Dao {

    int update(String sql, Object... args) throws SQLException;

    <T> T query(String sql, ResultSetExtractor<T> dataExtract, Object... args) throws SQLException;
}
