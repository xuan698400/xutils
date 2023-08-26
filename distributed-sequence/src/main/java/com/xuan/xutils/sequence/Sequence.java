package com.xuan.xutils.sequence;

/**
 * 序列号生成器
 *
 * @author xuan
 * @date 2018/1/10
 */
public interface Sequence {

    /**
     * 生成下一个序列号
     *
     * @return 序列号
     * @throws SequenceException 序列号异常
     */
    long nextValue() throws SequenceException;
}
