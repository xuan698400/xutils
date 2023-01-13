package com.xuan.moho.tools.ratelimiter;

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
    boolean tryAcquire();
}
