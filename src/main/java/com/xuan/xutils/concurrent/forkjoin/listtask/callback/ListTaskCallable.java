package com.xuan.xutils.concurrent.forkjoin.listtask.callback;


import java.util.List;

/**
 * 一个大任务被切割成小任务后，每个小任务需要执行的回调
 * <p>
 * Created by xuan on 17/8/23.
 */
public interface ListTaskCallable<T, R> {
    /**
     * 回调，入参是需要处理的数据，返回是处理后的数据
     *
     * @param tList
     * @return
     */
    List<R> call(List<T> tList);
}
