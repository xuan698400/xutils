package com.xuan.xutils.base.tools.ratelimiter.rolling;

import com.xuan.xutils.base.tools.ratelimiter.RateLimiter;

/**
 * @author xuan
 * @since 2019/5/23
 */
public class RollingRateLimiter implements RateLimiter {

    private double limitQps;
    private RollingNumber rollingNumber;

    public RollingRateLimiter(double limitQps) {
        this.limitQps = limitQps;
        rollingNumber = new RollingNumber();
    }

    @Override
    public boolean tryPass() {
        rollingNumber.addOne(CounterDataType.QPS);

        double passedQps = rollingNumber.getAvg(CounterDataType.PASSED);
        if (passedQps > limitQps) {
            rollingNumber.addOne(CounterDataType.BLOCKED);
            return false;
        }

        rollingNumber.addOne(CounterDataType.PASSED);
        return true;
    }

    @Override
    public double getTotalQps() {
        return rollingNumber.getAvg(CounterDataType.QPS);
    }

    @Override
    public double getPassedQps() {
        return rollingNumber.getAvg(CounterDataType.PASSED);
    }

    @Override
    public double getBlockedQps() {
        return rollingNumber.getAvg(CounterDataType.BLOCKED);
    }

}
