package com.xuan.xutils.concurrent.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/**
 * 自定义一个线程池
 *
 * Created by xuan on 17/8/14.
 */
public interface XThreadPool extends Executor {

    /**
     * 提交任务
     *
     * @param task
     * @return
     */
    Future<?> submit(Runnable task);

    /**
     * 提交任务
     *
     * @param task
     * @param <T>
     * @return
     */
    <T> Future<T> submit(Callable<T> task);


}
