package com.xuan.xutils.design.spi.extspi;

/**
 * 实现2
 *
 * @author xuan
 * @since 2022/11/18
 */
public class ExtSpiImpl2 implements ExtSpi {

    @Override
    public boolean support(String bizCode) {
        return "bizCode2".equals(bizCode);
    }

    @Override
    public String execute(String params) {
        return "ExtSpiImpl2_Accept[" + params + "]";
    }

}
