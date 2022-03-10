package com.xuan.mix.concurrent.batchtask;

import com.xuan.mix.concurrent.batchtask.executor.parallel.BatchTaskExecutorParallelImpl;

/**
 * 任务执行器工厂类,最好可以使用Spring方式配置Bean，这里只是提供了API方式
 * <p>
 *
 * @author xuan
 * @date 17/8/29
 */
public class BatchTaskExecutorFactory {

    /**
     * 获取ListTask执行器
     *
     * @return
     */
    public static <T, R> BatchTaskExecutor<T, R> getParallelExecutor() {
        return new BatchTaskExecutorParallelImpl<>();
    }

}
