package com.xuan.xutils.concurrent.forkjoin.listtask.bak;

import java.util.List;

/**
 * 任务执行回调
 * <p>
 * Created by xuan on 17/8/23.
 */
public interface ListTaskCallable<T, R> {
    /**
     * 回调，如参是需要处理的数据，返回是处理后的数据
     *
     * @param list
     * @return
     */
    List<R> call(List<T> list);
}
