package com.xuan.mix.an.dao.sql;

import java.util.List;

/**
 * @author xuan
 * @since 2021/11/5
 */
public class SqlModel {
    /**
     * 带问号的SQL
     */
    private String sql;

    /**
     * 参数
     */
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
