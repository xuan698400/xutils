package com.xuan.common.concurrent.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuan
 * @since 2021/1/26
 */
public class Main {

    private static AtomicInteger count = new AtomicInteger(0);

    private static final ThreadPoolExecutor EXECUTOR = ParallelExecutorFactory.getBizThreadPoolExecutor();

    public static void main(String[] args) {

        List<Runnable> runnableList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            runnableList.add(() -> {
                System.out.println(
                    "我是线程：" + Thread.currentThread().getName() + ", 我加一次count：" + count.incrementAndGet());
            });
        }

        ParallelExecutor.execute(EXECUTOR, runnableList, 1000);
    }

}
