package com.xuan.dao.model;

/**
 * @author xuan
 * @since 2021/11/5
 */
public interface BaseDO {

    /**
     * 表名
     *
     * @return String
     */
    String tableName();

    /**
     * 主键
     *
     * @return Long
     */
    Long primaryKey();
}
