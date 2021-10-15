package com.xuan.mix.domain.middleware.sequence;

/**
 * @author xuan
 * @since 2021/10/15
 */
public interface Sequence {

    Long createId(SequenceType sequenceType);
}
