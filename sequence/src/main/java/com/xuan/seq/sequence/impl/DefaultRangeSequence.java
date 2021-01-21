package com.xuan.seq.sequence.impl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.xuan.seq.exception.SeqException;
import com.xuan.seq.range.SeqRange;
import com.xuan.seq.range.SeqRangeMgr;
import com.xuan.seq.sequence.RangeSequence;

/**
 * 序列号区间生成器接口默认实现
 *
 * @author xuan
 * @date 2018/1/10
 */
public class DefaultRangeSequence implements RangeSequence {

    /**
     * 获取区间是加一把独占锁防止资源冲突
     */
    private final Lock lock = new ReentrantLock();

    /**
     * 序列号区间管理器
     */
    private SeqRangeMgr seqRangeMgr;

    /**
     * 当前序列号区间
     */
    private volatile SeqRange currentRange;

    /**
     * 需要获取区间的业务名称
     */
    private String name;

    @Override
    public long nextValue() throws SeqException {
        //当前区间不存在，重新获取一个区间
        if (null == currentRange) {
            lock.lock();
            try {
                if (null == currentRange) {
                    currentRange = seqRangeMgr.nextRange(name);
                }
            } finally {
                lock.unlock();
            }
        }

        //当value值为-1时，表明区间的序列号已经分配完，需要重新获取区间
        long value = currentRange.getAndIncrement();
        if (value == -1) {
            lock.lock();
            try {
                for (; ; ) {
                    if (currentRange.isOver()) {
                        currentRange = seqRangeMgr.nextRange(name);
                    }

                    value = currentRange.getAndIncrement();
                    if (value == -1) {
                        continue;
                    }

                    break;
                }
            } finally {
                lock.unlock();
            }
        }

        if (value < 0) {
            throw new SeqException("Sequence value overflow, value = " + value);
        }

        return value;
    }

    @Override
    public void setSeqRangeMgr(SeqRangeMgr seqRangeMgr) {
        this.seqRangeMgr = seqRangeMgr;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
