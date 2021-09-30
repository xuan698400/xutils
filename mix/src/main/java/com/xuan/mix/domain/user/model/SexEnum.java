package com.xuan.mix.domain.user.model;

/**
 * @author xuan
 * @since 2021/8/15
 */
public enum SexEnum {
    //
    MAN(1, "男"),
    WOMAN(2, "女");

    private int value;
    private String msg;

    SexEnum(int value, String msg) {
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
