package com.xuan.spring.utils.extp.model;

/**
 * @author xuan
 * @since 2021/7/30
 */
public enum PriorityEnum {

    //
    HIGH(100),
    MIDDLE(50),
    LOW(0);

    private int value;

    PriorityEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
