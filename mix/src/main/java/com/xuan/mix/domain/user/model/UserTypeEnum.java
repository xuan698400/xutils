package com.xuan.mix.domain.user.model;

/**
 * @author xuan
 * @since 2021/8/15
 */
public enum UserTypeEnum {
    //
    NORMAL(1, "正常"),
    DELETED(-1, "被删除的");

    private int value;
    private String msg;

    UserTypeEnum(int value, String msg) {
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
