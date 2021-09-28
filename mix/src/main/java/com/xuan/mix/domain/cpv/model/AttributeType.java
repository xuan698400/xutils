package com.xuan.mix.domain.cpv.model;

/**
 * @author xuan
 * @since 2021/9/28
 */
public enum AttributeType {

    //
    SPU_ATTRIBUTE(1, "关键属性，用来标准商品"),
    SKU_ATTRIBUTE(2, "销售属性，用来确定最小库存价格等");

    private int value;
    private String msg;

    AttributeType(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }

}
