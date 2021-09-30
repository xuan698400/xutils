package com.xuan.mix.domain.share.model;

/**
 * @author xuan
 * @since 2021/8/15
 */
public enum ContactType {
    //
    PHONE(1, "手机号"),
    TEL(2, "座机号"),
    QQ(3, "qq号"),
    EMAIL(4, "电子邮件"),
    ;

    private int value;
    private String msg;

    ContactType(int value, String msg) {
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
