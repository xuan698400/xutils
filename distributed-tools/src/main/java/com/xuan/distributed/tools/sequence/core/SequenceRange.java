package com.xuan.distributed.tools.sequence.core;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 序列号区间对象模型
 *
 * @author xuan
 * @date 2018/1/10
 */
public class SequenceRange {
    /**
     * 区间的序列号开始值
     */
    private final long min;
    /**
     * 区间的序列号结束值
     */
    private final long max;
    /**
     * 区间的序列号当前值
     */
    private final AtomicLong value;
    /**
     * 区间的序列号是否分配完毕，每次分配完毕就会去重新获取一个新的区间
     */
    private volatile boolean over = false;

    public SequenceRange(long min, long max) {
        this.min = min;
        this.max = max;
        this.value = new AtomicLong(min);
    }

    public long getAndIncrement() {
        long currentValue = value.getAndIncrement();
        if (currentValue > max) {
            over = true;
            return -1;
        }

        return currentValue;
    }

    public boolean isOver() {
        return over;
    }

}
