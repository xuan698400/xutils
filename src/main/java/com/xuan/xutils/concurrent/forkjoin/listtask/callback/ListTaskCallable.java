package com.xuan.xutils.concurrent.forkjoin.listtask.callback;


import java.util.List;

/**
 * 任务的逻辑回调。尽管框架会自动帮你拆分成很多子任务，但是具体需要执行的逻辑还是由用户自己实现。
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
