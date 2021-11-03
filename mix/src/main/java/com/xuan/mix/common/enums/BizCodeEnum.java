package com.xuan.mix.common.enums;

/**
 * bizCode错误码定义规范
 * <p>
 *
 * @author xuan
 * @since 2021/7/12
 */
public enum BizCodeEnum {
    /**
     * 业务身份枚举
     */
    BIZ1("BIZ1", "业务1"),
    BIZ2("BIZ2", "业务2"),
    ;

    private String bizCode;

    private String desc;

    BizCodeEnum(String bizCode, String desc) {
        this.bizCode = bizCode;
        this.desc = desc;
    }

    public String getBizCode() {
        return bizCode;
    }

    public String getDesc() {
        return desc;
    }

}
