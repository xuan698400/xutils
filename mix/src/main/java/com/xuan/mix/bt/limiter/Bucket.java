package com.xuan.mix.bt.limiter;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author xuan
 * @since 2019/5/22
 */
public class Bucket {
    final private long windowStart;

    final LongAdder[] statistics;

    Bucket(long startTime) {
        this.windowStart = startTime;

        // 初始化统计数组
        statistics = new LongAdder[CounterDataType.values().length];
        for (CounterDataType dataType : CounterDataType.values()) {
            statistics[dataType.ordinal()] = new LongAdder();
        }
    }

    long get(CounterDataType dataType) {
        return statistics[dataType.ordinal()].sum();
    }

    LongAdder getAdder(CounterDataType dataType) {
        return statistics[dataType.ordinal()];
    }

    public long getWindowStart() {
        return windowStart;
    }

}
