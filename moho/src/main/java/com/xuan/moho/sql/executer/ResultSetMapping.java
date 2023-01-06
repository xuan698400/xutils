package com.xuan.moho.sql.executer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 结果集映射处理器
 *
 * @author xuan
 * @since 2022/9/21
 */
public interface ResultSetMapping<T> {

    /**
     * 映射结果集到对象
     *
     * @param resultSet 结果集
     * @return 对象
     * @throws SQLException SQLException
     */
    T extract(ResultSet resultSet) throws SQLException;
}
