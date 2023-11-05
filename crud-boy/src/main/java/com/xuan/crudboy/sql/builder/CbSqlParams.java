package com.xuan.crudboy.sql.builder;

import java.util.List;

/**
 * 带有SQL和参数的模型
 *
 * @author xuan
 * @since 2021/11/5
 */
public class CbSqlParams {
    /**
     * 带问号的SQL
     */
    private String sql;
    /**
     * 参数
     */
    private List<Object> params;

    public Object[] buildParamsArray() {
        return null == params ? null : params.toArray(new Object[0]);
    }

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
