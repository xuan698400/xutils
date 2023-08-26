package com.xuan.xutils.sequence.impl.range;

import com.xuan.xutils.sequence.SequenceException;

/**
 * 序列号区间管理器，服务器每次向一个存储介子获取一段数字区间，然后在内存进行分配
 *
 * @author xuan
 * @date 2018/1/10
 */
public interface RangeManager {

    /**
     * 获取指定区间名的下一个区间
     *
     * @param name 区间名
     * @return 返回区间
     * @throws SequenceException 异常
     */
    Range nextRange(String name) throws SequenceException;

    /**
     * 初始化
     */
    void init();
}
