package com.xuan.sequence.range.db;

import javax.sql.DataSource;

import com.xuan.sequence.common.SeqException;
import com.xuan.sequence.range.RangeModel;
import com.xuan.sequence.range.SeqRangeMgr;

/**
 * DB区间管理器
 *
 * @author xuan
 * @date 2018/4/29
 */
public class DbSeqRangeMgr implements SeqRangeMgr {

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
     * DB来源
     */
    private DataSource dataSource;

    @Override
    public RangeModel nextRange(String name) throws SeqException {
        if (isEmpty(name)) {
            throw new SecurityException("[DbSeqRangeMgr-nextRange] name is empty.");
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
                return new RangeModel(oldValue + 1, newValue);
            }
            //else 失败重试
        }

        throw new SeqException("Retried too many times, retryTimes = " + getRetryTimes());
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
            throw new SecurityException("[DbSeqRangeMgr-checkParam] step must greater than 0.");
        }
        if (stepStart < 0) {
            throw new SecurityException("[DbSeqRangeMgr-setStepStart] stepStart < 0.");
        }
        if (retryTimes <= 0) {
            throw new SecurityException("[DbSeqRangeMgr-setRetryTimes] retryTimes must greater than 0.");
        }
        if (null == dataSource) {
            throw new SecurityException("[DbSeqRangeMgr-setDataSource] dataSource is null.");
        }
    }

    ////////getter and setter

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
