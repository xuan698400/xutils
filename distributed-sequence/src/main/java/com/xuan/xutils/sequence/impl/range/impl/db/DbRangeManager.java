package com.xuan.xutils.sequence.impl.range.impl.db;

import javax.sql.DataSource;

import com.xuan.xutils.sequence.SequenceException;
import com.xuan.xutils.sequence.impl.range.Range;
import com.xuan.xutils.sequence.impl.range.RangeManager;

/**
 * 使用DB实现序列号区间
 *
 * @author xuan
 * @date 2018/4/29
 */
public class DbRangeManager implements RangeManager {

    /**
     * 区间步长
     */
    private int step = 1000;

    /**
     * 区间起始位置，真实从stepStart+1开始
     */
    private long stepStart = 0;

    /**
     * 获取区间失败重试次数
     */
    private int retryTimes = 100;

    /**
     * 数据源
     */
    private DataSource dataSource;

    @Override
    public Range nextRange(String name) throws SequenceException {
        if (isEmpty(name)) {
            throw new SecurityException("Sequence range name is empty.");
        }

        Long oldValue;
        long newValue;

        for (int i = 0; i < getRetryTimes(); i++) {
            oldValue = DbHelper.selectRange(getDataSource(), name, getStepStart());

            if (null == oldValue) {
                //区间不存在，重试
                continue;
            }

            newValue = oldValue + getStep();

            if (DbHelper.updateRange(getDataSource(), newValue, oldValue, name)) {
                return new Range(oldValue + 1, newValue);
            }
            //else 失败重试
        }

        throw new SequenceException("Retried too many times, retryTimes = " + getRetryTimes());
    }

    @Override
    public void init() {
        checkParam();
        DbHelper.createTable(getDataSource());
    }

    private boolean isEmpty(String str) {
        return null == str || str.trim().length() == 0;
    }

    private void checkParam() {
        if (step <= 0) {
            throw new SecurityException("Sequence range step must greater than 0.");
        }
        if (stepStart < 0) {
            throw new SecurityException("Sequence range stepStart must greater than or equal 0.");
        }
        if (retryTimes <= 0) {
            throw new SecurityException("Sequence range retryTimes must greater than 0.");
        }
        if (null == dataSource) {
            throw new SecurityException("Sequence range dataSource must not null.");
        }
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public long getStepStart() {
        return stepStart;
    }

    public void setStepStart(long stepStart) {
        this.stepStart = stepStart;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
