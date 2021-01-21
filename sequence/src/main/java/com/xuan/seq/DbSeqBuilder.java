package com.xuan.seq;

import javax.sql.DataSource;

import com.xuan.seq.range.impl.db.DbSeqRangeMgr;
import com.xuan.seq.sequence.Sequence;
import com.xuan.seq.sequence.impl.DefaultRangeSequence;

/**
 * 基于DB取步长，序列号生成器构建者
 *
 * @author xuan
 * @date 2018/5/30
 */
public class DbSeqBuilder implements SeqBuilder {

    /**
     * 数据库数据源[必选]
     */
    private DataSource dataSource;

    /**
     * 业务名称[必选]
     */
    private String bizName;
    /**
     * 存放序列号步长的表[可选：默认：sequence]
     */
    private String tableName = "sequence";
    /**
     * 并发是数据使用了乐观策略，这个是失败重试的次数[可选：默认：100]
     */
    private int retryTimes = 100;
    /**
     * 获取range步长[可选：默认：1000]
     */
    private int step = 1000;

    /**
     * 序列号分配起始值[可选：默认：0]
     */
    private long stepStart = 0;

    @Override
    public Sequence build() {
        //使用DB获取区间管理器
        DbSeqRangeMgr dbSeqRangeMgr = new DbSeqRangeMgr();
        dbSeqRangeMgr.setDataSource(this.dataSource);
        dbSeqRangeMgr.setTableName(this.tableName);
        dbSeqRangeMgr.setRetryTimes(this.retryTimes);
        dbSeqRangeMgr.setStep(this.step);
        dbSeqRangeMgr.setStepStart(stepStart);
        dbSeqRangeMgr.init();
        //构建序列号生成器
        DefaultRangeSequence sequence = new DefaultRangeSequence();
        sequence.setName(this.bizName);
        sequence.setSeqRangeMgr(dbSeqRangeMgr);
        return sequence;
    }

    public static DbSeqBuilder create() {
        DbSeqBuilder builder = new DbSeqBuilder();
        return builder;
    }

    public DbSeqBuilder dataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public DbSeqBuilder tableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public DbSeqBuilder retryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
        return this;
    }

    public DbSeqBuilder step(int step) {
        this.step = step;
        return this;
    }

    public DbSeqBuilder bizName(String bizName) {
        this.bizName = bizName;
        return this;
    }

    public DbSeqBuilder stepStart(long stepStart) {
        this.stepStart = stepStart;
        return this;
    }

}
