package com.xuan.common.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xuan
 * @since 2021/1/26
 */
public class ThreadPoolExecutorFactory {

    private static ThreadFactory BIZ_THREAD_FACTORY = new ThreadFactoryBuilder()
        .setNameFormat("biz-thread-%d").build();

    private static ThreadPoolExecutor BIZ_THREAD_POOL_EXECUTER = new ThreadPoolExecutor(8, 8,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>(1000), BIZ_THREAD_FACTORY, new ThreadPoolExecutor.AbortPolicy());

    public static ThreadPoolExecutor getBizThreadPoolExecutor() {
        return BIZ_THREAD_POOL_EXECUTER;
    }

}
