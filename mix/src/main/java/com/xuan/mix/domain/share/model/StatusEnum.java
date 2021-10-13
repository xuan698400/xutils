package com.xuan.mix.domain.share.model;

/**
 * @author xuan
 * @since 2021/8/15
 */
public enum StatusEnum {
    //
    NORMAL(1, "正常"),
    DELETED(-1, "删除");

    private int value;
    private String msg;

    StatusEnum(int value, String msg) {
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
