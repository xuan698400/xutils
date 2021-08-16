package com.xuan.mix.concurrent.listtask;

import com.xuan.mix.concurrent.listtask.executor.CyclicBarrierExecutorImpl;
import com.xuan.mix.concurrent.listtask.executor.ListTaskExecutor;
import com.xuan.mix.concurrent.listtask.executor.ListTaskExecutorImpl;

/**
 * 任务执行器工厂类,最好可以使用Spring方式配置Bean，这里只是提供了API方式
 * <p>
 *
 * @author xuan
 * @date 17/8/29
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
