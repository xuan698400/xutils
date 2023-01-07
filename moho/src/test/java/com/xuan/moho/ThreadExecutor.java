package com.xuan.moho;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuan
 * @since 2023/1/7
 */
public class ThreadExecutor {

    private static AtomicInteger num = new AtomicInteger(1);

    private static ThreadPoolExecutor WORKER_EXECUTOR = new ThreadPoolExecutor(20, 50, 4,
        TimeUnit.MINUTES, new LinkedBlockingQueue<>(2000), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "WORKER_EXECUTOR_" + num.incrementAndGet());
        }
    });

    public static void submit(Runnable runnable) {
        WORKER_EXECUTOR.submit(runnable);
    }

}
