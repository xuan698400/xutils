package com.xuan.crudboy.sql.executer;

import java.sql.SQLException;
import java.util.List;

import com.xuan.crudboy.sql.mapping.CbResultSetMapping;

/**
 * @author xuan
 * @since 2022/9/21
 */
public interface CbSQLExecuter {

    /**
     * 插入数据操作，并返回自增ID
     *
     * @param sql  SQL
     * @param args 参数
     * @return 自增ID
     * @throws SQLException SQLException
     */
    long insertBackId(String sql, Object... args) throws SQLException;

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
     * @param sql              SQL
     * @param resultSetMapping 结果集映射
     * @param args             参数
     * @param <T>              T
     * @return 结果数据列表
     * @throws SQLException SQLException
     */
    <T> List<T> queryList(String sql, CbResultSetMapping<T> resultSetMapping, Object... args) throws SQLException;

    /**
     * 查询单个
     *
     * @param sql              SQL
     * @param resultSetMapping 结果集映射
     * @param args             参数
     * @param <T>              T
     * @return 结果对象
     * @throws SQLException SQLException
     */
    <T> T queryObject(String sql, CbResultSetMapping<T> resultSetMapping, Object... args) throws SQLException;
}
