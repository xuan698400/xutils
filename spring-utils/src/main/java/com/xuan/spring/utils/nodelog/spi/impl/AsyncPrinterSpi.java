package com.xuan.spring.utils.nodelog.spi.impl;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.xuan.spring.utils.nodelog.spi.LoggerSpi;
import com.xuan.spring.utils.nodelog.spi.PrinterSpi;

/**
 * @author xuan
 * @since 2021/9/3
 */
public class AsyncPrinterSpi implements PrinterSpi {

    /**
     * corePoolSize: 4
     * maxPoolSize: 4
     * keepAliveSeconds: 4
     * unit: TimeUnit.MINUTES
     * workQueue: LinkedBlockingQueue(1000)
     * threadFactory:name:Thread_LbsResultChecker
     * rejectedExecutionHandler: 非强逻辑，拒绝策略直接忽略
     */
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4, 4,
        TimeUnit.MINUTES, new LinkedBlockingQueue<>(1000), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "LogPrinter");
        }
    },
        new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                //Ignore
            }
        });

    @Override
    public void print(LoggerSpi loggerSpi, String msg) {
        executor.submit(() -> loggerSpi.info(msg));
    }

}
