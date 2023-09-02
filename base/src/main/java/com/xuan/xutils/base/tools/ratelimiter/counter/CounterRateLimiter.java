package com.xuan.xutils.base.tools.ratelimiter.counter;

import java.util.concurrent.atomic.AtomicLong;

import com.xuan.xutils.base.tools.ratelimiter.RateLimiter;

/**
 * 计数器限流实现
 *
 * @author xuan
 * @since 2023/1/13
 */
public class CounterRateLimiter implements RateLimiter {

    /**
     * 区间开始时间
     */
    private long start;
    /**
     * 区间间隔，单位毫秒
     */
    private long interval;
    /**
     * 没区间内可通过数
     */
    private long limitQps;
    /**
     * 总调用量
     */
    private AtomicLong totalCount;
    /**
     * 通过的数量
     */
    private AtomicLong passedCount;
    /**
     * 被阻塞的数量
     */
    private AtomicLong blockedCount;

    @Override
    public boolean tryPass() {
        long now = System.currentTimeMillis();
        if (now >= start + interval) {
            //不在区间内进行重置
            doReset(now);
        }
        return doTryAcquire(start, now);
    }

    @Override
    public double getTotalQps() {
        return 0;
    }

    @Override
    public double getPassedQps() {
        return 0;
    }

    @Override
    public double getBlockedQps() {
        return 0;
    }

    private void doReset(long now) {
        synchronized (this) {
            if (now >= start + interval) {
                totalCount.set(0L);
                passedCount.set(0L);
                blockedCount.set(0L);
                start = now;
            }
        }
    }

    private boolean doTryAcquire(long start, long now) {
        boolean isAcquired = totalCount.incrementAndGet() <= limitQps;
        if (isAcquired) {
            passedCount.incrementAndGet();
        } else {
            blockedCount.incrementAndGet();
        }
        return isAcquired;
    }

    public static class Builder {
        private long interval;
        private long limitQps;

        public Builder interval(long interval) {
            this.interval = interval;
            return this;
        }

        public Builder limitQps(long limitQps) {
            this.limitQps = limitQps;
            return this;
        }

        public CounterRateLimiter build() {
            checkParams();
            CounterRateLimiter limiter = new CounterRateLimiter();
            limiter.interval = this.interval;
            limiter.limitQps = this.limitQps;
            limiter.start = System.currentTimeMillis();
            limiter.totalCount = new AtomicLong(0);
            limiter.passedCount = new AtomicLong(0);
            limiter.blockedCount = new AtomicLong(0L);
            return limiter;
        }

        public void checkParams() {
            if (interval <= 0L) {
                throw new IllegalArgumentException(String.format("interval[%s] invalid", interval));
            }
            if (limitQps <= 0L) {
                throw new IllegalArgumentException(String.format("limitQps[%s] invalid", limitQps));
            }
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
