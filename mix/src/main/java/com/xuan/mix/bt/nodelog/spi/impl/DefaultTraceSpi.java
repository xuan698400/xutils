package com.xuan.mix.bt.nodelog.spi.impl;

import com.xuan.mix.bt.nodelog.spi.TraceSpi;

/**
 * 默认为空
 *
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
