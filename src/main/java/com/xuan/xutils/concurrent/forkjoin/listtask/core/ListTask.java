package com.xuan.xutils.concurrent.forkjoin.listtask.core;

import com.xuan.xutils.concurrent.forkjoin.listtask.callback.ListTaskCallable;
import com.xuan.xutils.concurrent.forkjoin.listtask.config.ListTaskConfig;

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
     * 配置参数
     */
    private ListTaskConfig config;

    /**
     * 任务需要处理的原始数据
     */
    private List<T> originList;

    /**
     * 处理任务回调
     */
    private ListTaskCallable<T, R> callable;

    public ListTask(List<T> originList, ListTaskCallable<T, R> callable, ListTaskConfig config) {
        if (null == originList || originList.size() == 0) {
            throw new RuntimeException("[ListTask-ListTask] originList is empty.");
        }
        if (null == callable) {
            throw new RuntimeException("[ListTask-ListTask] callable is null.");
        }
        if (null == config) {
            throw new RuntimeException("[ListTask-ListTask] config is null.");
        }
        this.originList = originList;
        this.callable = callable;
        this.config = config;
    }

    @Override
    protected ListTaskResult<R> compute() {
        if (originList.size() <= config.getSubOriginListSize()) {
            //任务需要处理的数据已经达到或者小于指定数量，才去执行真正逻辑
            List<R> resultList = callable.call(originList);
            ListTaskResult<R> result = new ListTaskResult<R>();
            result.setList(resultList);
            return result;
        } else {
            //拆分任务
            List<ListTask<T, R>> taskList = new ArrayList<>();
            int subTaskSize = originList.size() / config.getSubOriginListSize();
            if (originList.size() % config.getSubOriginListSize() > 0) {
                subTaskSize += 1;
            }

            for (int i = 0; i < subTaskSize; i++) {
                int start = i * config.getSubOriginListSize();
                int end = i * config.getSubOriginListSize() + config.getSubOriginListSize();
                if (end > originList.size()) {
                    //最后一个任务的情况下
                    end = originList.size();
                }
                List<T> subList = originList.subList(start, end);
                ListTask<T, R> subTask = new ListTask<>(subList, callable, config);
                subTask.fork();
                taskList.add(subTask);
            }
            //合并任务结果
            ListTaskResult<R> result = new ListTaskResult<>();
            for (ListTask<T, R> task : taskList) {
                result.mergeFrom(task.join());
            }
            return result;
        }
    }

}
