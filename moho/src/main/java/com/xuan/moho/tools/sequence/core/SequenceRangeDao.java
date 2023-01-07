package com.xuan.moho.tools.sequence.core;

import com.xuan.moho.tools.sequence.SequenceException;

/**
 * @author xuan
 * @since 2022/9/9
 */
public interface SequenceRangeDao {

    /**
     * 创建区间表
     *
     * @throws SequenceException SequenceException
     */
    void createRangeTable() throws SequenceException;

    /**
     * 初始化区间
     *
     * @param name  区间名
     * @param value 区间初始化值
     * @throws SequenceException SequenceException
     */
    void initRange(String name, long value) throws SequenceException;

    /**
     * 更新区间
     *
     * @param name     区间名
     * @param newValue 更新值
     * @param oldValue 原值
     * @return 更新是否成功
     * @throws SequenceException SequenceException
     */
    boolean updateRange(String name, Long newValue, Long oldValue) throws SequenceException;

    /**
     * 查询区间值
     *
     * @param name 区间名
     * @return 区间值
     * @throws SequenceException SequenceException
     */
    Long selectRange(String name) throws SequenceException;
    
}
