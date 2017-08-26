package com.xuan.xutils.concurrent.forkjoin.listtask;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * 把一个List任务进行分解并发执行，对使用者又可以同步拿到数据
 * <p>
 * Created by xuan on 17/8/23.
 */
public class ListTaskExcutor<T, R> {

    /**
     * 执行提交的任务
     *
     * @param orignList 根据orignList来拆分任务，并行执行
     * @param callable  具体任务执行逻辑，还是要用户自己实现
     * @param config    一些定制配置参数，高级用户使用，小白用户可以使用下面提供的便捷方法
     * @return
     */
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

    /**
     * 执行提交的任务(默认配置，一个数据拆分成一个任务)
     *
     * @param orignList 根据orignList来拆分任务，并行执行
     * @param callable  具体任务执行逻辑，还是要用户自己实现
     * @return
     */
    public ListTaskResult<R> execute(List<T> orignList, ListTaskCallable<T, R> callable) {
        ListTaskConfig config = new ListTaskConfig();
        config.setSubOriginListSize(1);
        return execute(orignList, callable, config);
    }

    /**
     * 执行提交的任务(默认配置，一个数据拆分成一个任务)
     *
     * @param orignList         根据orignList来拆分任务，并行执行
     * @param callable          具体任务执行逻辑，还是要用户自己实现
     * @param subOriginListSize 多少个待处理数据为一个小任务
     * @return
     */
    public ListTaskResult<R> execute(List<T> orignList, ListTaskCallable<T, R> callable, int subOriginListSize) {
        ListTaskConfig config = new ListTaskConfig();
        config.setSubOriginListSize(subOriginListSize);
        return execute(orignList, callable, config);
    }

}
