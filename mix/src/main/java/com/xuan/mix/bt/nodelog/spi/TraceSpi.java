package com.xuan.mix.bt.nodelog.spi;

/**
 * 链路信息补充扩展，分布式系统中，往往会有traceId、rpcId信息，实现他可以进行补充输出
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
