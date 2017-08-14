package com.xuan.xutils.domain.enums;

/**
 * 性别枚举
 * <p>
 * Created by xuan on 17/8/2.
 */
public enum SexEnum {

    MAN(1, "男"), WOMAN(2, "女");

    private int code;

    private String msg;

    SexEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据code获取到SexEnum,取不到返回null
     *
     * @param code
     * @return
     */
    public static SexEnum codeOf(int code) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.getCode() == code) {
                return sexEnum;
            }
        }
        return null;
    }

    /**
     * 判断入参code是否包含
     *
     * @param code
     * @return
     */
    public static boolean isContain(int code) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.getCode() == code) {
                return true;
            }
        }
        return false;
    }

    /**
     * isContain取反
     *
     * @param code
     * @return
     */
    public static boolean isNotContain(int code) {
        return !isContain(code);
    }

    /**
     * 判断类型是否相同
     *
     * @param code
     * @return
     */
    public boolean isEq(int code) {
        return this.code == code;
    }

    /**
     * isEq取反
     *
     * @param code
     * @return
     */
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
