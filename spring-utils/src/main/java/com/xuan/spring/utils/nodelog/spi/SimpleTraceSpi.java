package com.xuan.spring.utils.nodelog.spi;

import com.xuan.mix.bt.nodelog.spi.TraceSpi;

/**
 * @author xuan
 * @since 2021/12/21
 */
public class SimpleTraceSpi implements TraceSpi {

    @Override
    public String getTraceId() {
        return "traceId_123";
    }

    @Override
    public String getRpcId() {
        return "rpcId_456";
    }
}
