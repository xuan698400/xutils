package com.xuan.crudboy.sql.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 结果集映射处理器
 *
 * @author xuan
 * @since 2022/9/21
 */
public interface CbResultSetMapping<T> {

    /**
     * 映射结果集到对象
     *
     * @param resultSet 结果集
     * @return 对象
     * @throws SQLException SQLException
     */
    T extract(ResultSet resultSet) throws SQLException;
}
