package com.xuan.xutils.design.spi;

/**
 * 实现1
 *
 * @author xuan
 * @since 2022/11/18
 */
public class ExtSpiImpl1 implements ExtSpi {

    @Override
    public boolean support(String bizCode) {
        return "bizCode1".equals(bizCode);
    }

    @Override
    public String execute(String params) {
        return "ExtSpiImpl1_Accept[" + params + "]";
    }

}
