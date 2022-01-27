package com.xuan.dao.common;

/**
 * 数据模型基类
 *
 * @author xuan
 * @since 2021/11/5
 */
public interface DataModel {

    /**
     * 指定数据模型对应表的表名
     *
     * @return String
     */
    String tableName();

    /**
     * 设置对应表的主键
     *
     * @return NameValuePair
     */
    NameValuePair primaryKey();

    /**
     * 数据版本，如果指定了，在更新时会进行并发校验
     *
     * @return NameValuePair
     */
    default NameValuePair version() {
        return null;
    }

}
