package com.xuan.seq.sequence;

import com.xuan.seq.range.SeqRangeMgr;

/**
 * 序列号区间生成器接口
 * Created by xuan on 2018/5/6.
 */
public interface RangeSequence extends Sequence {

    /**
     * 设置区间管理器
     *
     * @param seqRangeMgr 区间管理器
     */
    void setSeqRangeMgr(SeqRangeMgr seqRangeMgr);

    /**
     * 设置获取序列号名称
     *
     * @param name 名称
     */
    void setName(String name);
}
