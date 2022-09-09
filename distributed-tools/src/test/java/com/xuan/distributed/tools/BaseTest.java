package com.xuan.distributed.tools;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * Created by xuan on 2018/5/31.
 */
public class BaseTest {

    private static AtomicInteger num = new AtomicInteger(1);

    private static ThreadPoolExecutor WORKER_EXECUTOR = new ThreadPoolExecutor(20, 50, 4,
        TimeUnit.MINUTES, new LinkedBlockingQueue<>(2000), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "WORKER_EXECUTOR_" + num.incrementAndGet());
        }
    });

    private static DruidDataSource dataSource;

    static {
        dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/bpmweb?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("bpmweb");
        dataSource.setPassword("123456");
        dataSource.setMaxActive(300);
        dataSource.setMinIdle(50);
        dataSource.setInitialSize(2);
        dataSource.setMaxWait(500);
    }

    protected static DruidDataSource getDataSource() {
        return dataSource;
    }

    protected static void submit(Runnable runnable) {
        WORKER_EXECUTOR.submit(runnable);
    }

    protected static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            //
        }
    }

    protected static void log(String msg) {
        System.out.println(String.format("线程[%s]：%s", Thread.currentThread().getName(), msg));
    }

}
