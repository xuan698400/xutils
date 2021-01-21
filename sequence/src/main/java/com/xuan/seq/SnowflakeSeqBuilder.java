package com.xuan.seq;

import com.xuan.seq.sequence.Sequence;
import com.xuan.seq.sequence.impl.SnowflakeSequence;

/**
 * 基于雪花算法，序列号生成器构建者
 *
 * @author xuan
 * @date 2018/5/30
 */
public class SnowflakeSeqBuilder implements SeqBuilder {

    /**
     * 数据中心ID，值的范围在[0,31]之间，一般可以设置机房的IDC[必选]
     */
    private long datacenterId;
    /**
     * 工作机器ID，值的范围在[0,31]之间，一般可以设置机器编号[必选]
     */
    private long workerId;

    @Override
    public Sequence build() {
        SnowflakeSequence sequence = new SnowflakeSequence();
        sequence.setDatacenterId(this.datacenterId);
        sequence.setWorkerId(this.workerId);
        return sequence;
    }

    public static SnowflakeSeqBuilder create() {
        SnowflakeSeqBuilder builder = new SnowflakeSeqBuilder();
        return builder;
    }

    public SnowflakeSeqBuilder datacenterId(long datacenterId) {
        this.datacenterId = datacenterId;
        return this;
    }

    public SnowflakeSeqBuilder workerId(long workerId) {
        this.workerId = workerId;
        return this;
    }

}
