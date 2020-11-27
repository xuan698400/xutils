package com.xuan.rbac.common;

/**
 * @author xuan
 * @since 2019/11/17
 */
public enum StatusEnum {

    //正常
    NORMAL(0, "正常"),
    DELETED(-1, "被删除"),
    ;

    private int value;
    private String name;

    StatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.name();
    }

    public static StatusEnum valueOf(Integer value) {
        if (null == value) {
            return null;
        }

        StatusEnum result = null;
        for (StatusEnum e : StatusEnum.values()) {
            if (e.getValue() == value) {
                result = e;
            }
        }
        return result;
    }

}
