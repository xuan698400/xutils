package com.xuan.mix.biz.category.model;

/**
 * @author xuan
 * @since 2022/3/30
 */
public enum CategoryStatus {
    //
    NORMAL(1, "正常"),
    DELETED(-1, "软删除");

    private int value;
    private String msg;

    CategoryStatus(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }

}
