package com.xuan.distributed.tools.dao;

import java.sql.ResultSet;

/**
 * @author xuan
 * @since 2022/9/21
 */
public interface ResultSetExtractor<T> {
    T extract(ResultSet resultSet);
}
