package com.xuan.seq;

import com.xuan.seq.sequence.Sequence;

/**
 * 序列号生成器构建者
 *
 * @author xuan
 * @date 2018/5/30
 */
public interface SeqBuilder {

    /**
     * 构建一个序列号生成器
     *
     * @return 序列号生成器
     */
    Sequence build();
}
