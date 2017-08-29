package com.xuan.xutils.concurrent.forkjoin.listtask.core;

import com.xuan.xutils.concurrent.forkjoin.listtask.callback.ListTaskCallable;
import com.xuan.xutils.concurrent.forkjoin.listtask.config.ListTaskConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * 任务核心代码，这个任务主要做两件事
 * 第一：如果原数据足够多，就会进行任务拆分，拆分到原数据足够小的子任务
 * 第二：如果原数据足够少，调用用户传入的逻辑回调拿到结果数据
 * <p>
 * Created by xuan on 17/8/23.
 */
public class ListTask<T, R> extends RecursiveTask<ListTaskResult<R>> {
    private static final long serialVersionUID = 1;

    /**
     * 配置参数
     */
    private ListTaskConfig config;

    /**
     * 任务需要处理的原始数据
     */
    private List<T> originList;

    /**
     * 用户逻辑回调
     */
    private ListTaskCallable<T, R> callable;

    /**
     * 构造函数
     *
     * @param originList 原数据
     * @param callable   逻辑处理回调
     * @param config     配置参数
     */
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
            //如果原数据足够少，即小于配置参数指定的单个任务的原数据数量时，进行用户的业务逻辑回调执行，拿到结果
            List<R> resultList = callable.call(originList);
            ListTaskResult<R> result = new ListTaskResult<R>();
            result.setList(resultList);
            return result;
        } else {
            //如果原数据足够多，那么需要进行任务拆分，根据配置，拆分成多个子任务
            //例如：原数据=20个，用户配置子任务的原数据数量subOriginListSize=3
            //那么：会拆分任务：3，3，3，3，3，3，2一共7个子任务
            List<ListTask<T, R>> taskList = new ArrayList<>();
            int subTaskSize = originList.size() / config.getSubOriginListSize();
            if (originList.size() % config.getSubOriginListSize() > 0) {
                //未除尽有余数，需要额外再加一个
                subTaskSize += 1;
            }
            for (int i = 0; i < subTaskSize; i++) {
                int start = i * config.getSubOriginListSize();
                int end = i * config.getSubOriginListSize() + config.getSubOriginListSize();
                if (end > originList.size()) {
                    //最后一个任务，未必刚好是subOriginListSize的数量，所以end值进行调整
                    end = originList.size();
                }
                List<T> subList = originList.subList(start, end);
                ListTask<T, R> subTask = new ListTask<>(subList, callable, config);
                subTask.fork();
                taskList.add(subTask);
            }
            //合并任务执行结果
            ListTaskResult<R> result = new ListTaskResult<>();
            for (ListTask<T, R> task : taskList) {
                result.mergeFrom(task.join());
            }
            return result;
        }
    }

}
