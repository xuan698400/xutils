package com.xuan.mix.concurrent.batchtask;

import java.util.List;

/**
 * 如果用户想更方便，只需要对原始数据单个单个处理，可以继承这个去实现。
 * 如果子任务拥有多个原始数据需要处理，这里会for循环进行串行处理
 * <p>
 *
 * @author xuan
 * @date 17/8/29
 */
public abstract class AbstractSingleSizeBatchTaskCallable<T, R> extends AbstractBatchTaskCallable<T, R> {

    @Override
    public void call(List<T> subList, BatchSubTaskResult<R> subTaskResult) {
        for (T t : subList) {
            call(t, subTaskResult);
        }
    }

    /**
     * 子类实现，处理单个原始数据
     *
     * @param t             单个数据
     * @param subTaskResult 处理结果
     */
    protected abstract void call(T t, BatchSubTaskResult<R> subTaskResult);

}
