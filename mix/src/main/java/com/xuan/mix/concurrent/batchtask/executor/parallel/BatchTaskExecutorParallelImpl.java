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
    private static ExecutorService defaultExecutor = new ThreadPoolExecutor(10, 10, 4,
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
        checkParams(result, originList, callable, config);
        if (!result.isSuccess()) {
            return result;
        }

        //2. 构建分批任务以及设置分批结果
        Wrapper<R> wrapper = buildTaskAndResult(originList, callable, config);

        //3. 提交任务到线程池
        long startTime = System.currentTimeMillis();
        List<Future> futureList = new ArrayList<>();
        for (ParallelTask subTask : wrapper.getSubTaskList()) {
            ExecutorService executor = null != config.getCustomExecutor() ? config.getCustomExecutor()
                : defaultExecutor;
            Future future = executor.submit(subTask);
            futureList.add(future);
        }

        // 4. 等待所有任务执行完毕（或者超时）
        try {
            wrapper.getCountDownLatch().await((config.getTimeout() - (System.currentTimeMillis() - startTime)),
                TimeUnit.MILLISECONDS);
            if (wrapper.getCountDownLatch().getCount() > 0) {
                for (Future future : futureList) {
                    if (null != future && !future.isDone()) {
                        future.cancel(true);
                    }
                }
                result.setSuccess(false);
                result.setResultMsg("Timeout, some sub tasks are not executed.");
            }
        } catch (InterruptedException ie) {
            //Ignore
        }

        result.setSubTaskResultList(wrapper.getSubTaskResultList());
        return result;
    }

    @Override
    public BatchTaskResult<R> execute(List<T> originList, BatchTaskCallable<T, R> callable, int subOriginListSize,
        int timeout) {
        BatchTaskConfig config = new BatchTaskConfig();
        config.setSubOriginListSize(subOriginListSize);
        config.setTimeout(timeout);
        return execute(originList, callable, config);
    }

    private void checkParams(BatchTaskResult<R> result, List<T> originList,
        BatchTaskCallable<T, R> callable,
        BatchTaskConfig config) {

        if (null == originList || originList.isEmpty()) {
            result.setSuccess(false);
            result.setResultMsg("originList is empty.");
            return;
        }

        if (null == callable) {
            result.setSuccess(false);
            result.setResultMsg("callable is null.");
            return;
        }

        if (null == config) {
            result.setSuccess(false);
            result.setResultMsg("config is null.");
            return;
        }

        if (config.getTimeout() <= 0) {
            result.setSuccess(false);
            result.setResultMsg("Timeout less than or equal to 0.");
            return;
        }

        if (config.getSubOriginListSize() <= 0) {
            result.setSuccess(false);
            result.setResultMsg("SubOriginListSize less than or equal to 0.");
        }
    }

    private Wrapper<R> buildTaskAndResult(List<T> originList,
        BatchTaskCallable<T, R> callable,
        BatchTaskConfig config) {

        Wrapper<R> wrapper = new Wrapper();

        List<ParallelTask> subTaskList = new ArrayList<>();
        wrapper.setSubTaskList(subTaskList);

        List<BatchSubTaskResult<R>> subTaskResultList = new ArrayList<>();
        wrapper.setSubTaskResultList(subTaskResultList);

        //切割数据
        List<List<T>> subOriginLists = ParallelTaskHelper.split(originList, config.getSubOriginListSize());

        CountDownLatch countDownLatch = new CountDownLatch(subOriginLists.size());
        wrapper.setCountDownLatch(countDownLatch);

        //构建任务和结果
        for (List<T> subOriginList : subOriginLists) {
            BatchSubTaskResult<R> subTaskResult = BatchSubTaskResult.of();
            subTaskResultList.add(subTaskResult);
            ParallelTask parallelTask = new ParallelTask(countDownLatch,
                () -> callable.call(subOriginList, subTaskResult));
            subTaskList.add(parallelTask);
        }

        return wrapper;
    }

}
