package com.xuan.xutils.concurrent.forkjoin.listtask.bak;

import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * 列表任务并行执行
 * <p>
 * Created by xuan on 17/8/23.
 */
public class ListTask<T, R> extends RecursiveTask<ListTaskResult<T, R>> {
    /**
     * 待处理原数据
     */
    private List<T> list;

    /**
     * 处理任务回调
     */
    private ListTaskCallable<T, R> listTaskCallable;

    /**
     * 切分任务粒度，默认：1
     */
    private int subTaskSize = 1;

    public ListTask(List<T> list, ListTaskCallable<T, R> listTaskCallable, int subTaskSize) {
        if (null == list || list.size() == 0) {
            throw new RuntimeException("[ListTask-ListTask] list is empty.");
        }
        if (null == listTaskCallable) {
            throw new RuntimeException("[ListTask-ListTask] listTaskCallable is null.");
        }
        this.list = list;
        this.listTaskCallable = listTaskCallable;
        this.subTaskSize = subTaskSize;
    }

    public ListTask(List<T> list, ListTaskCallable<T, R> listTaskCallable) {
        this(list, listTaskCallable, 1);
    }

    @Override
    protected ListTaskResult<T, R> compute() {
        if (list.size() <= subTaskSize) {
            ListTaskResult<T, R> listTaskResult = new ListTaskResult();
            List<R> subResultList = listTaskCallable.call(list);
            listTaskResult.appendResult(list, subResultList);
            return listTaskResult;
        } else {
            //拆分任务
            int midSize = list.size() / 2;
            ListTask<T, R> left = new ListTask(list.subList(0, midSize), listTaskCallable);
            ListTask<T, R> right = new ListTask(list.subList(midSize, list.size()), listTaskCallable);
            left.fork();
            right.fork();

            ListTaskResult<T, R> leftReuslt = left.join();
            ListTaskResult<T, R> rightReuslt = right.join();
            return leftReuslt.merge(rightReuslt);
        }
    }

}
