package com.xuan.distributed.tools.sequence.core;

import com.xuan.distributed.tools.sequence.SequenceException;

/**
 * @author xuan
 * @since 2022/9/9
 */
public interface SequenceRangeDao {

    void createRangeTable() throws SequenceException;

    void initRange(String name, long value) throws SequenceException;

    boolean updateRange(String name, Long newValue, Long oldValue) throws SequenceException;

    Long selectRange(String name) throws SequenceException;
}
