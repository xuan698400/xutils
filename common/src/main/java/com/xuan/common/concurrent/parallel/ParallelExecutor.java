package com.xuan.common.concurrent.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xuan
 * @since 2021/1/26
 */
public class ParallelExecutor {

    public static void execute(ThreadPoolExecutor executor, List<Runnable> runnableList, long timeout) {
        try {
            CountDownLatch countDownLatch = new CountDownLatch(runnableList.size());
            long startTime = System.currentTimeMillis();

            // 并行执行
            List<Future> futureList = new ArrayList<>(runnableList.size());
            for (Runnable runnable : runnableList) {
                ParallelTask parallelTask = new ParallelTask(countDownLatch, runnable);
                try {
                    //设置主线程的线程变量到并行任务中去
                    parallelTask.setBizContext(ParallelThreadLocal.get());
                    //提交任务到线程池
                    Future<?> future = executor.submit(parallelTask);
                    //记录Future，方便后续取消任务
                    futureList.add(future);
                } catch (RejectedExecutionException e) {
                    boolean cancel = parallelTask.cancel();
                    System.out.println(String
                        .format("ParallelExecutor_Execute_Submit_RejectedExecutionException. cancel:%s, msg:%s", cancel,
                            e.getMessage()));
                }
            }

            // 等待所有任务执行完毕（或者超时）
            countDownLatch.await((timeout - (System.currentTimeMillis() - startTime)), TimeUnit.MILLISECONDS);
            // 如果countDownLatch计数器没有完成，说明超时，取消其他任务
            if (countDownLatch.getCount() > 0) {
                for (Future future : futureList) {
                    if (null != future && !future.isDone()) {
                        future.cancel(true);
                    }
                }
            }
        } catch (InterruptedException ie) {
            System.out.println("ParallelExecutor_InterruptedException.");
        }
    }

}
