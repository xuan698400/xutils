package com.xuan.xutils.concurrent.forkjoin.listtask.executor;

import com.xuan.xutils.concurrent.forkjoin.listtask.callback.ListTaskCallable;
import com.xuan.xutils.concurrent.forkjoin.listtask.config.ListTaskConfig;
import com.xuan.xutils.concurrent.forkjoin.listtask.core.ListTask;
import com.xuan.xutils.concurrent.forkjoin.listtask.core.ListTaskResult;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * 把一个List任务进行分解并发执行，对使用者又可以同步拿到数据
 * <p>
 * Created by xuan on 17/8/23.
 */
public class ListTaskExecutorImpl<T, R> implements ListTaskExecutor<T, R> {
    @Override
    public ListTaskResult<R> execute(List<T> orignList, ListTaskCallable<T, R> callable, ListTaskConfig config) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ListTask<T, R> listTask = new ListTask<T, R>(orignList, callable, config);
        Future<ListTaskResult<R>> future = forkJoinPool.submit(listTask);
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        } catch (ExecutionException e) {
            e.fillInStackTrace();
        }
        return null;
    }

    @Override
    public List<R> execute(List<T> orignList, ListTaskCallable<T, R> callable) {
        ListTaskConfig config = new ListTaskConfig();
        config.setSubOriginListSize(1);
        return execute(orignList, callable, config).getList();
    }

    @Override
    public List<R> execute(List<T> orignList, ListTaskCallable<T, R> callable, int subOriginListSize) {
        ListTaskConfig config = new ListTaskConfig();
        config.setSubOriginListSize(subOriginListSize);
        return execute(orignList, callable, config).getList();
    }

}
