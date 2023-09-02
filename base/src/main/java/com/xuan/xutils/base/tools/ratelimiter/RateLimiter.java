package com.xuan.xutils.base.tools.ratelimiter;

/**
 * @author xuan
 * @since 2023/1/13
 */
public interface RateLimiter {
    /**
     * 判断是否能通过
     *
     * @return true/false
     */
    boolean tryPass();

    /**
     * 平均每秒调用次数
     *
     * @return
     */
    double getTotalQps();

    /**
     * 平均每秒通过次数
     *
     * @return
     */
    double getPassedQps();

    /**
     * 平均每秒阻塞次数
     *
     * @return
     */
    double getBlockedQps();
}
