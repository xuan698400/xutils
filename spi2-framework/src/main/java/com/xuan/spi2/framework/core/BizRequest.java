package com.xuan.spi2.framework.core;

/**
 * @author xuan
 * @since 2020/11/16
 */
public abstract class BizRequest {

    private String bizCode;

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizCode() {
        return bizCode;
    }

}
