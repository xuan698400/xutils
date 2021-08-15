package com.xuan.mix.common.enums;

/**
 * @author xuan
 * @since 2021/8/15
 */
public enum YnEnum {
    //
    YES(true, 1, "是"),
    NO(false, 0, "否");

    private boolean value;

    private int code;

    private String msg;

    YnEnum(boolean value, int code, String msg) {
        this.value = value;
        this.code = code;
        this.msg = msg;
    }
    
    public static YnEnum codeOf(int code) {
        for (YnEnum ynEnum : YnEnum.values()) {
            if (ynEnum.code == code) {
                return ynEnum;
            }
        }
        return null;
    }

    public boolean isValue() {
        return value;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
