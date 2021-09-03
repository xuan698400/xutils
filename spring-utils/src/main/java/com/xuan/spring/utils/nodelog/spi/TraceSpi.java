package com.xuan.spring.utils.nodelog.spi;

/**
 * 链路信息补充扩展
 *
 * @author xuan
 * @since 2021/9/3
 */
public interface TraceSpi {

    /**
     * traceId
     *
     * @return traceId
     */
    String getTraceId();

    /**
     * rpcId
     *
     * @return rpcId
     */
    String getRpcId();
}
