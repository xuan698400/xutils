package com.xuan.xutils.sequence.impl.range;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 区间对象模型
 *
 * @author xuan
 * @date 2018/1/10
 */
public class Range {
    /**
     * 区间的序列号结束值
     */
    private final long end;
    /**
     * 区间的序列号当前值
     */
    private final AtomicLong value;
    /**
     * 区间的序列号是否分配完毕，每次分配完毕就会去重新获取一个新的区间
     */
    private volatile boolean over = false;

    public Range(long start, long end) {
        this.end = end;
        this.value = new AtomicLong(start);
    }

    public long getAndIncrement() {
        long currentValue = value.getAndIncrement();
        if (currentValue > end) {
            over = true;
            return -1;
        }

        return currentValue;
    }

    public boolean isOver() {
        return over;
    }

}
