package com.xuan.dao.utils;

/**
 * @author xuan
 * @since 2021/11/5
 */
public class InsertSql {

    private String sql;

    private Object[] params;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

}
