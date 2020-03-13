package com.xuan.user.enums;

/**
 * @author xuan
 * @since 2019/11/17
 */
public enum UserStatusEnum {

    //正常
    NORMAL(0, "正常"),
    DELETED(-1, "被删除"),
    WAIT_CHECK(1, "被确认状态"),
    ;

    private int value;
    private String name;

    UserStatusEnum(int value, String name) {
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

    public static UserStatusEnum valueOf(Integer value) {
        if (null == value) {
            return null;
        }

        UserStatusEnum result = null;
        for (UserStatusEnum e : UserStatusEnum.values()) {
            if (e.getValue() == value) {
                result = e;
            }
        }
        return result;
    }

}
