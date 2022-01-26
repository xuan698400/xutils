package com.xuan.dao.sqlbuilder;

import java.util.List;

/**
 * @author xuan
 * @since 2021/11/5
 */
public class SqlModel {
    private String sql;

    private List<Object> params;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }

}
