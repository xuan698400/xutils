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
    private String desc;

    SexEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SexEnum of(int code) {
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

    public String getDesc() {
        return desc;
    }
}
