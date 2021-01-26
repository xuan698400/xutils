package com.xuan.common.concurrent.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import com.xuan.common.concurrent.ThreadPoolExecutorFactory;

/**
 * @author xuan
 * @since 2021/1/26
 */
public class ParallelTest {

    private static AtomicInteger count = new AtomicInteger(0);

    private static final ThreadPoolExecutor executor = ThreadPoolExecutorFactory.getBizThreadPoolExecutor();

    public static void main(String[] args) {

        List<Runnable> runnableList = new ArrayList<>();

        //用10个并发任务分别给count加10次
        for (int i = 0; i < 10; i++) {
            runnableList.add(() -> {
                System.out.println(
                    "我是线程：" + Thread.currentThread().getName() + ", 我加一次count：" + count.incrementAndGet());
            });
        }

        ParallelExecutor.execute(executor, runnableList, 1000);
    }
}
