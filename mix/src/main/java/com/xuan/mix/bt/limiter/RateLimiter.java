package com.xuan.mix.bt.limiter;

/**
 * @author xuan
 * @since 2019/5/23
 */
public class RateLimiter {

    private double limitQps;
    private RollingNumber rollingNumber;

    public RateLimiter(double limitQps) {
        this.limitQps = limitQps;
        rollingNumber = new RollingNumber();
    }

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

    public double getQps() {
        return rollingNumber.getAvg(CounterDataType.QPS);
    }

    public double getPassed() {
        return rollingNumber.getAvg(CounterDataType.PASSED);
    }

    public double getBlocked() {
        return rollingNumber.getAvg(CounterDataType.BLOCKED);
    }

}
