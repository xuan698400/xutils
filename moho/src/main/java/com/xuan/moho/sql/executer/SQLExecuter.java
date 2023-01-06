package com.xuan.moho.sql.executer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author xuan
 * @since 2022/9/21
 */
public interface SQLExecuter {

    /**
     * 更新数据操作
     *
     * @param sql  SQL
     * @param args 参数
     * @return 影响行数
     * @throws SQLException SQLException
     */
    int update(String sql, Object... args) throws SQLException;

    /**
     * 查询数据操作
     *
     * @param sql         SQL
     * @param dataExtract 结果集映射
     * @param args        参数
     * @param <T>         T
     * @return 结果数据列表
     * @throws SQLException SQLException
     */
    <T> List<T> query(String sql, ResultSetMapping<T> dataExtract, Object... args) throws SQLException;

    /**
     * 查询数据操作，一行数据映射成MAP
     *
     * @param sql  SQL
     * @param args 参数
     * @return 结果数据列表
     * @throws SQLException SQLException
     */
    List<Map<String, Object>> query(String sql, Object... args) throws SQLException;
}
