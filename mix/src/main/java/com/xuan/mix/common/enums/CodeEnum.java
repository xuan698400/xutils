package com.xuan.mix.common.enums;

/**
 * 结果状态码的枚举
 * <p>
 * Created by xuan on 17/8/2.
 */
public enum CodeEnum {
    //
    ERROR(0, "错误"),
    SUCCESS(1, "成功");

    private int code;
    private String msg;

    CodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static boolean isContain(int code) {
        for (CodeEnum codeEnum : CodeEnum.values()) {
            if (codeEnum.getCode() == code) {
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

    public CodeEnum codeOf(int code) {
        for (CodeEnum codeEnum : CodeEnum.values()) {
            if (codeEnum.getCode() == code) {
                return codeEnum;
            }
        }
        return null;
    }

}
