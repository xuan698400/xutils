package com.xuan.spring.utils.nodelog.spi.impl;

import com.xuan.spring.utils.nodelog.spi.TraceSpi;

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
