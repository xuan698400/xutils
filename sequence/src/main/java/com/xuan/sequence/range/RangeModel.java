package com.xuan.sequence.range;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 序列号区间对象模型
 *
 * @author xuan
 * @date 2018/1/10
 */
public class RangeModel {

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

    public RangeModel(long min, long max) {
        this.min = min;
        this.max = max;
        this.value = new AtomicLong(min);
    }

    /**
     * 返回并递增下一个序列号
     *
     * @return 下一个序列号，如果返回-1表示序列号分配完毕
     */
    public long getAndIncrement() {
        long currentValue = value.getAndIncrement();
        if (currentValue > max) {
            over = true;
            return -1;
        }

        return currentValue;
    }

    public long getMin() {
        return min;
    }

    public long getMax() {
        return max;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    @Override
    public String toString() {
        return "max: " + max + ", min: " + min + ", value: " + value;
    }

}
