package com.xuan.moho.sql.orm.sqlparams;

import java.util.List;

/**
 * 带有SQL和参数的模型
 *
 * @author xuan
 * @since 2021/11/5
 */
public class SQLParams {
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
