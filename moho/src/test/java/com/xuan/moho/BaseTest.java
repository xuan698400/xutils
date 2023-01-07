package com.xuan.moho;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.druid.pool.DruidDataSource;

import org.junit.Before;

/**
 * Created by xuan on 2018/5/31.
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

    protected DruidDataSource dataSource;

    @Before
    public void init() {
        dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/bpmweb?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("bpmweb");
        dataSource.setPassword("123456");
        dataSource.setMaxActive(300);
        dataSource.setMinIdle(50);
        dataSource.setInitialSize(2);
        dataSource.setMaxWait(500);
    }

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
