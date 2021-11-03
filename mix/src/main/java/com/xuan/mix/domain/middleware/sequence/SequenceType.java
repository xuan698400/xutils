package com.xuan.mix.domain.middleware.sequence;

/**
 * @author xuan
 * @since 2021/9/28
 */
public enum SequenceType {

    //
    USER(1, "用户id"),
    CATEGORY(2, "类目id");

    private int value;
    private String msg;

    SequenceType(int value, String msg) {
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
