package com.xuan.mix.common.enums;

/**
 * @author xuan
 * @since 2021/8/15
 */
public enum SexEnum {
    //
    MAN(1, "男"),
    WOMAN(2, "女");

    private int code;
    private String msg;

    SexEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static SexEnum codeOf(int code) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.getCode() == code) {
                return sexEnum;
            }
        }
        return null;
    }

    public static boolean isContain(int code) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.getCode() == code) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotContain(int code) {
        return !isContain(code);
    }

    public boolean isEq(int code) {
        return this.code == code;
    }

    public boolean isNotEq(int code) {
        return !isEq(code);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
