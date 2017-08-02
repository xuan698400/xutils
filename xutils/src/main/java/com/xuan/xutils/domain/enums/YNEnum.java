package com.xuan.xutils.domain.enums;

/**
 * 一个通用的区分对错的枚举
 * <p>
 * Created by xuan on 17/8/2.
 */
public enum YNEnum {

    Y(true, 1, "是"), N(false, 0, "否");

    /**
     * true / false
     */
    private boolean value;

    /**
     * 1 / 0
     */
    private int code;

    /**
     * 是 / 否
     */
    private String msg;

    YNEnum(boolean value, int code, String msg) {
        this.value = value;
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据code获取到YNEnum,取不到返回YNEnum.N
     *
     * @param code
     * @return
     */
    public static YNEnum codeOf(int code) {
        for (YNEnum ynEnum : YNEnum.values()) {
            if (ynEnum.code == code) {
                return ynEnum;
            }
        }
        return null;
    }

}
