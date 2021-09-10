package com.xuan.mix.concurrent.batchtask.executor.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

import com.xuan.mix.concurrent.batchtask.BatchSubTaskResult;
import com.xuan.mix.concurrent.batchtask.BatchTaskCallable;
import com.xuan.mix.concurrent.batchtask.BatchTaskConfig;
import com.xuan.mix.concurrent.batchtask.BatchTaskExecutor;
import com.xuan.mix.concurrent.batchtask.BatchTaskResult;

/**
 * @author xuan
 * @since 2021/9/10
 */
public class BatchTaskExecutorParallelImpl<T, R> implements BatchTaskExecutor<T, R> {

    /**
     * 当线程处理不过来时，主线程执行
     */
    private static ExecutorService defaultExecutor = new ThreadPoolExecutor(4, 4, 4,
        TimeUnit.MINUTES, new LinkedBlockingQueue<>(1000), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "BatchTaskExecutorParallelImpl");
        }
    }, new CallerRunsPolicy());

    @Override
    public BatchTaskResult<R> execute(List<T> originList, BatchTaskCallable<T, R> callable, BatchTaskConfig config) {

        BatchTaskResult<R> result = new BatchTaskResult<>();

        //1. 入参基本校验
        if (null == originList || originList.isEmpty()) {
            return result;
        }

        if (null == callable) {
            result.setSuccess(false);
            result.setResultMsg("callable is null.");
            return result;
        }

        if (null == config) {
            result.setSuccess(false);
            result.setResultMsg("config is null.");
            return result;
        }

        //2. 数据切割
        List<List<T>> subOriginLists = ParallelTaskHelper.split(originList, config.getSubOriginListSize());
        List<BatchSubTaskResult<R>> subTaskResultList = new ArrayList<>();
        result.setSubTaskResultList(subTaskResultList);

        //3. 分批任务构建
        CountDownLatch countDownLatch = new CountDownLatch(subOriginLists.size());
        List<ParallelTask> parallelTaskList = new ArrayList<>();
        for (List<T> subOriginList : subOriginLists) {
            BatchSubTaskResult<R> subTaskResult = BatchSubTaskResult.of();
            subTaskResultList.add(subTaskResult);
            ParallelTask parallelTask = new ParallelTask(countDownLatch,
                () -> callable.call(subOriginList, subTaskResult));
            parallelTaskList.add(parallelTask);
        }

        //4. 提交任务到线程池
        long startTime = System.currentTimeMillis();
        List<Future> futureList = new ArrayList<>();
        for (ParallelTask parallelTask : parallelTaskList) {
            ExecutorService executor = null != config.getCustomExecutor() ? config.getCustomExecutor()
                : defaultExecutor;
            Future future = executor.submit(parallelTask);
            futureList.add(future);
        }

        // 5. 等待所有任务执行完毕（或者超时）
        try {
            countDownLatch.await((config.getTimeoutSecond() * 1000 - (System.currentTimeMillis() - startTime)),
                TimeUnit.MILLISECONDS);
        } catch (InterruptedException ie) {
            //Ignore
        }
        // 如果countDownLatch计数器没有完成，说明超时，取消其他任务
        if (countDownLatch.getCount() > 0) {
            for (Future future : futureList) {
                if (null != future && !future.isDone()) {
                    future.cancel(true);
                }
            }
            result.setSuccess(false);
            result.setResultMsg("Timeout, some subtasks are not executed.");
        }

        return result;
    }

    @Override
    public BatchTaskResult<R> execute(List<T> originList, BatchTaskCallable<T, R> callable, int subOriginListSize,
        int timeout) {
        BatchTaskConfig config = new BatchTaskConfig();
        config.setSubOriginListSize(subOriginListSize);
        config.setTimeoutSecond(timeout);
        return execute(originList, callable, config);
    }

}
