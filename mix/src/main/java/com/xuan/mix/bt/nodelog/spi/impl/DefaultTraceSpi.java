package com.xuan.mix.bt.nodelog.spi.impl;

import com.xuan.mix.bt.nodelog.spi.TraceSpi;

/**
 * @author xuan
 * @since 2021/9/3
 */
public class DefaultTraceSpi implements TraceSpi {
    @Override
    public String getTraceId() {
        return "-";
    }

    @Override
    public String getRpcId() {
        return "-";
    }

}
