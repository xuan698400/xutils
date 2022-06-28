package com.xuan.dao.common;

/**
 * 数据模型
 *
 * @author xuan
 * @since 2021/11/5
 */
public interface DataModel {

    /**
     * 表名
     *
     * @return String
     */
    String tableName();

    /**
     * 表主键
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
