package com.xuan.xutils.concurrent.forkjoin.listtask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * 列表任务并行执行
 * <p>
 * Created by xuan on 17/8/23.
 */
public class ListTask<T, R> extends RecursiveTask<ListTaskResult<R>> {

    /**
     * 待处理原数据
     */
    private List<T> originList;

    /**
     * 被任务分割后存放的原数据
     */
    private T origin;

    /**
     * 处理任务回调
     */
    private ListTaskCallable<T, R> callable;

    public ListTask(List<T> originList, ListTaskCallable<T, R> callable) {
        if (null == originList || originList.size() == 0) {
            throw new RuntimeException("[ListTask-ListTask] originList is empty.");
        }
        if (null == callable) {
            throw new RuntimeException("[ListTask-ListTask] callable is null.");
        }
        this.originList = originList;
        this.callable = callable;
    }

    /**
     * 内部使用
     *
     * @param origin
     * @param callable
     */
    private ListTask(T origin, ListTaskCallable<T, R> callable) {
        this.origin = origin;
        this.callable = callable;
    }

    @Override
    protected ListTaskResult<R> compute() {
        if (null != origin) {
            R r = callable.call(origin);
            ListTaskResult<R> result = new ListTaskResult<R>();
            result.setR(r);
            return result;
        } else {
            ListTaskResult<R> result = new ListTaskResult<R>();

            //切割小任务
            List<ListTask> subTaskList = new ArrayList<>();
            for (T t : originList) {
                ListTask<T, R> subTask = new ListTask(t, callable);
                subTask.fork();
                subTaskList.add(subTask);
            }
            //合并数据
            for (ListTask<T, R> subTask : subTaskList) {
                ListTaskResult<R> subResult = subTask.join();
                result.getList().add(subResult.getR());
            }
            return result;
        }
    }

}
