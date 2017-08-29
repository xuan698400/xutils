package com.xuan.xutils.concurrent.forkjoin.listtask;

import com.xuan.xutils.concurrent.forkjoin.listtask.executor.CyclicBarrierExecutorImpl;
import com.xuan.xutils.concurrent.forkjoin.listtask.executor.ListTaskExecutor;
import com.xuan.xutils.concurrent.forkjoin.listtask.executor.ListTaskExecutorImpl;

/**
 * 任务执行器工厂类,最好可以使用Spring方式配置Bean，这里只是提供了API方式
 * <p>
 * Created by xuan on 17/8/23.
 */
public abstract class ListTaskExecutorFactory {

    /**
     * 获取ListTask执行器
     *
     * @return
     */
    public static <T, R> ListTaskExecutor<T, R> getExecutor() {
        return new ListTaskExecutorImpl<>(10);
    }

    /**
     * 获取ListTask执行器
     *
     * @return
     */
    public static <T, R> ListTaskExecutor<T, R> getCyclicBarrierExecutor() {
        return new CyclicBarrierExecutorImpl<>();
    }

}
