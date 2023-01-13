package com.xuan.moho;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuan
 * @since 2023/1/13
 */
public class BaseTest {

    private AtomicInteger num = new AtomicInteger(1);

    private ThreadPoolExecutor WORKER_EXECUTOR = new ThreadPoolExecutor(20, 50, 4,
        TimeUnit.MINUTES, new LinkedBlockingQueue<>(2000), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "WORKER_EXECUTOR_" + num.incrementAndGet());
        }
    });

    protected void submit(Runnable runnable) {
        WORKER_EXECUTOR.submit(runnable);
    }

    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            //
        }
    }

    protected void log(String msg) {
        System.out.println(String.format("线程[%s]：%s", Thread.currentThread().getName(), msg));
    }
}
