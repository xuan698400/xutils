package com.xuan.hisql.service.model;

/**
 * @author xuan
 * @since 2022/8/23
 */
public enum DataSourceType {

    //
    MYSQL("MYSQL", "MYSQL");

    private String code;

    private String msg;

    DataSourceType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public boolean eq(DataSourceType other) {
        return null != other && other.getCode().equals(this.getCode());
    }

    public static DataSourceType of(String code) {
        for (DataSourceType e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

}
