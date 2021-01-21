package com.xuan.seq.range;

import com.xuan.seq.exception.SeqException;

/**
 * 序列号区间管理器，服务器每次向一个存储中介获取一段数字区间，然后在内存进行分配
 *
 * @author xuan
 * @date 2018/1/10
 */
public interface SeqRangeMgr {

    /**
     * 获取指定区间名的下一个区间
     *
     * @param name 区间名
     * @return 返回区间
     * @throws SeqException 异常
     */
    SeqRange nextRange(String name) throws SeqException;

    /**
     * 初始化
     */
    void init();
}
