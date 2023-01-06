package com.xuan.moho.sql.common;

/**
 * SQL语法类型，不同的存储系统SQL可能会有偏差
 *
 * @author xuan
 * @since 2021/12/26
 */
public enum SQLSyntax {
    /**
     * MYSQL
     */
    MYSQL("MYSQL", "标准MySQL语法"),
    ;

    private String code;

    private String msg;

    SQLSyntax(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
