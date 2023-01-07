package com.xuan.moho.tools.sequence.db;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.sql.DataSource;

import com.xuan.moho.tools.sequence.Sequence;
import com.xuan.moho.tools.sequence.SequenceException;
import com.xuan.moho.tools.sequence.core.SequenceRange;
import com.xuan.moho.tools.sequence.core.SequenceRangeDao;

/**
 * 序列号区间生成器接口默认实现
 *
 * @author xuan
 * @date 2018/1/10
 */
public class DbSequence implements Sequence {

    /**
     * 获取区间时加一把独占锁防止资源冲突
     */
    private final Lock lock = new ReentrantLock();
    /**
     * 当前序列号区间
     */
    private volatile SequenceRange currentRange;
    /**
     * 区间步长
     */
    private int step;
    /**
     * 区间起始位置，实际计数从+1开始
     */
    private long stepStart;
    /**
     * 获取区间失败重试次数
     */
    private int retryTimes;
    /**
     * 区间数据仓储
     */
    private SequenceRangeDao sequenceRangeDao;
    /**
     * 需要获取区间的业务名称
     */
    private String name;

    @Override
    public long nextValue() throws SequenceException {
        //当前区间不存在，重新获取一个区间
        if (null == currentRange) {
            lock.lock();
            try {
                if (null == currentRange) {
                    currentRange = nextRange();
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
                        currentRange = nextRange();
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

        return value;
    }

    private SequenceRange nextRange() throws SequenceException {
        Long oldValue;
        long newValue;

        for (int i = 0; i < this.retryTimes; i++) {
            oldValue = sequenceRangeDao.selectRange(name);
            if (null == oldValue) {
                //初始化后再重试
                sequenceRangeDao.initRange(name, stepStart);
                continue;
            }

            newValue = oldValue + step;

            if (sequenceRangeDao.updateRange(name, newValue, oldValue)) {
                return new SequenceRange(oldValue + 1, newValue);
            }
            //else 失败重试
        }
        throw new SequenceException("获取区间多次重试失败, 重试次数:" + this.retryTimes);
    }

    public static class Builder {
        private String name;
        private int step = 100;
        private long stepStart = 0;
        private int retryTimes = 20;
        private DataSource dataSource;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder step(int step) {
            this.step = step;
            return this;
        }

        public Builder stepStart(long stepStart) {
            this.stepStart = stepStart;
            return this;
        }

        public Builder retryTimes(int retryTimes) {
            this.retryTimes = retryTimes;
            return this;
        }

        public Builder dataSource(DataSource dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public DbSequence build() {
            checkParams();
            DbSequence dbSequence = new DbSequence();
            dbSequence.name = this.name;
            dbSequence.step = this.step;
            dbSequence.stepStart = this.stepStart;
            dbSequence.retryTimes = this.retryTimes;
            //初始化
            dbSequence.sequenceRangeDao = new DbSequenceRangeDao(dataSource);
            dbSequence.sequenceRangeDao.createRangeTable();
            return dbSequence;
        }

        private void checkParams() {
            if (null == name || name.trim().length() == 0) {
                throw new SequenceException("名称参数[name]必须大于0");
            }
            if (step <= 0) {
                throw new SequenceException("步长参数[step]必须大于0");
            }
            if (stepStart < 0) {
                throw new SequenceException("步长开始参数[stepStart]必须大于0");
            }
            if (retryTimes <= 0) {
                throw new SequenceException("失败重试次数参数[retryTimes]必须大于0");
            }
            if (null == dataSource) {
                throw new SequenceException("数据源参数[dataSource]不能为空");
            }
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
