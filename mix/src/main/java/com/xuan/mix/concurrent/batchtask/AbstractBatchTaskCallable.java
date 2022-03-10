package com.xuan.mix.concurrent.batchtask;

import java.util.List;

/**
 * 预留适配扩展，使用者最好继承这个抽象类来实现自己的业务逻辑，而非直接去实现BatchTaskCallable接口
 * 万一要扩展方法什么的，也好在这里默认实现做兼容，例如统计执行任务的时间啥的
 *
 * @author xuan
 * @date 17/8/29
 */
public abstract class AbstractBatchTaskCallable<T, R> implements BatchTaskCallable<T, R> {
    @Override
    public void call(List<T> subList, BatchSubTaskResult<R> subTaskResult) {
        doCall(subList, subTaskResult);
    }

    /**
     * 执行回调逻辑
     *
     * @param subList       子任务入参
     * @param subTaskResult 子任务结果
     */
    protected abstract void doCall(List<T> subList, BatchSubTaskResult<R> subTaskResult);
}
