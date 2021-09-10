package com.xuan.mix.concurrent.batchtask;

import java.util.List;

/**
 * 任务的逻辑回调。尽管框架会自动帮你拆分成很多子任务，但是具体需要执行的逻辑还是由用户自己实现。
 * <p>
 *
 * @author xuan
 * @date 17/8/29
 */
public interface BatchTaskCallable<T, R> {

    /**
     * 任务处理回调
     *
     * @param subList       分批数据集合
     * @param subTaskResult 处理后结果模型
     */
    void call(List<T> subList, BatchSubTaskResult<R> subTaskResult);
}
