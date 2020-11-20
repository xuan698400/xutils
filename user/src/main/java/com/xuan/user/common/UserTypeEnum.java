package com.xuan.user.common;

/**
 * @author xuan
 * @since 2019/11/17
 */
public enum UserTypeEnum {

    //
    NORMAL(1, "普通用户"),
    ADMIN(2, "管理员"),
    ;

    private int value;
    private String name;

    UserTypeEnum(int value, String name) {
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

    public static UserTypeEnum valueOf(Integer value) {
        if (null == value) {
            return null;
        }

        UserTypeEnum result = null;
        for (UserTypeEnum e : UserTypeEnum.values()) {
            if (e.getValue() == value) {
                result = e;
            }
        }
        return result;
    }

}
