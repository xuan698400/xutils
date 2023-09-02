package com.xuan.xutils.base.tools.ratelimiter.rolling;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuan
 * @since 2019/5/22
 */
public class RollingNumber {
    /**
     * 采样统计精度：10表示，1S分成10个桶，每个桶100MS来统计。值越小统计精度越高，当然就越费性能
     */
    private final int sampleCount = 10;
    /**
     * 统计间隔，例如这里设置2S，表示每次统计以2S为间隔，即获取20个桶进行平均
     */
    private final int intervals = 2;

    /**
     * 每个桶时间间隔：1000 / 10 = 100MS
     */
    private final int milliSecondsInBucket = 1000 / sampleCount;

    /**
     * 整个环形数组时间跨度：100MS * 10 * 2 = 2000MS
     */
    private final Integer timeInMilliseconds = milliSecondsInBucket * sampleCount * intervals;
    /**
     * 整个缓存数组桶的数量：10 * 2 = 20
     */
    private final int numberOfBuckets = sampleCount * intervals;

    private final BucketCircularArray buckets;

    private ReentrantLock newBucketLock = new ReentrantLock();

    public RollingNumber() {
        buckets = new BucketCircularArray(numberOfBuckets);
    }

    public void addOne(CounterDataType dataType) {
        add(dataType, 1L);
    }

    public void add(CounterDataType dataType, long value) {
        getCurrentBucket().getAdder(dataType).add(value);
    }

    public long getTotalSum(CounterDataType dataType) {
        Bucket lastBucket = getCurrentBucket();
        if (lastBucket == null) {
            return 0;
        }

        long sum = 0;
        for (Bucket b : buckets) {
            long tmp = b.getAdder(dataType).sum();
            sum += tmp;
        }
        return sum;
    }

    public double getAvg(CounterDataType counterDataType) {
        long latestRollingSum = getTotalSum(counterDataType);
        return (double)(latestRollingSum) / intervals;
    }

    private Bucket getCurrentBucket() {
        long currentTime = System.currentTimeMillis();

        //获取环型数组中尾部的桶
        Bucket currentBucket = buckets.peekLast();
        if (currentBucket != null && currentTime < currentBucket.getWindowStart() + getBucketSizeInMilliseconds()) {
            //获取到桶，并且当前的时间在桶的范围时间内
            return currentBucket;
        }
        if (newBucketLock.tryLock()) {
            try {
                if (null == currentBucket) {
                    Bucket newBucket = new Bucket(currentTime);
                    buckets.addLast(newBucket);
                    return newBucket;
                } else {
                    //开始循环补充中间空白的桶，直到定位到属于自己区间的桶
                    for (int i = 0; i < numberOfBuckets; i++) {
                        Bucket lastBucket = buckets.peekLast();
                        if (currentTime < lastBucket.getWindowStart() + getBucketSizeInMilliseconds()) {
                            //找到了合法的桶
                            return lastBucket;
                        } else if (currentTime - (lastBucket.getWindowStart() + getBucketSizeInMilliseconds())
                            > timeInMilliseconds) {
                            // 当前时间已经超出整个环形数组可用时间，那么重置环形数组
                            reset();
                            return getCurrentBucket();
                        } else {
                            //补充中间缺失的桶
                            buckets.addLast(
                                new Bucket(lastBucket.getWindowStart() + getBucketSizeInMilliseconds()));
                        }
                    }
                    return buckets.peekLast();
                }
            } finally {
                newBucketLock.unlock();
            }
        } else {
            //当获取不到锁，说明有其他线程正在创建新的桶，这里牺牲掉一部分准确性能，获取到当前时间最接近的桶
            currentBucket = buckets.peekLast();
            if (currentBucket != null) {
                return currentBucket;
            } else {
                try {
                    Thread.sleep(5);
                } catch (Exception e) {
                    // ignore
                }
                return getCurrentBucket();
            }
        }
    }

    private void reset() {
        buckets.clear();
    }

    private long getBucketSizeInMilliseconds() {
        return timeInMilliseconds / numberOfBuckets;
    }

}
