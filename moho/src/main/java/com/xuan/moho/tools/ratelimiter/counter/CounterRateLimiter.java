package com.xuan.moho.tools.ratelimiter.counter;

import java.util.concurrent.atomic.AtomicLong;

import com.xuan.moho.tools.ratelimiter.RateLimiter;

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
     * 监视器
     */
    private Monitor monitor;
    /**
     * 没区间内可通过数
     */
    private long limitQps;
    /**
     * 当前计数器
     */
    private AtomicLong count;

    @Override
    public boolean tryAcquire() {
        long now = System.currentTimeMillis();
        if (now >= start + interval) {
            //不在区间内进行重置
            doReset(now);
        }
        return doTryAcquire(start, now);
    }

    private void doReset(long now) {
        synchronized (this) {
            if (now >= start + interval) {
                count.set(0);
                start = now;
                if (null != monitor) {
                    monitor.reset(now);
                }
            }
        }
    }

    private boolean doTryAcquire(long start, long now) {
        boolean isAcquired = count.incrementAndGet() <= limitQps;
        if (null != monitor) {
            monitor.acquire(start, now, isAcquired);
        }
        return isAcquired;
    }

    public static class Builder {
        private long interval;
        private long limitQps;
        private Monitor monitor;

        public Builder interval(long interval) {
            this.interval = interval;
            return this;
        }

        public Builder limitQps(long limitQps) {
            this.limitQps = limitQps;
            return this;
        }

        public Builder monitor(Monitor monitor) {
            this.monitor = monitor;
            return this;
        }

        public CounterRateLimiter build() {
            checkParams();
            CounterRateLimiter limiter = new CounterRateLimiter();
            limiter.interval = this.interval;
            limiter.limitQps = this.limitQps;
            limiter.monitor = monitor;
            limiter.start = System.currentTimeMillis();
            limiter.count = new AtomicLong(0);
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

    public interface Monitor {

        /**
         * 触发获取操作，并得到是否成功
         *
         * @param start      当前区间开始时间
         * @param now        当前时间
         * @param isAcquired 是否成功
         */
        void acquire(long start, long now, boolean isAcquired);

        /**
         * 超出区间，进行重置
         *
         * @param newStart 重置开始时间
         */
        void reset(long newStart);
    }

}
