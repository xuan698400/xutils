package com.xuan.dao.common;

/**
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
     * 主键
     *
     * @return NameValuePair
     */
    default NameValuePair primaryKey() {return null;}

    /**
     * 数据版本，如果指定了，在更新时会进行并发校验
     *
     * @return NameValuePair
     */
    default NameValuePair version() {
        return null;
    }

}
