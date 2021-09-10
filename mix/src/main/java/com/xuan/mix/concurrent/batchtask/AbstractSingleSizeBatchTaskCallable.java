package com.xuan.mix.concurrent.batchtask;

import java.util.ArrayList;
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
    public List<BatchSubTaskResult<R>> call(List<T> list) {
        List<BatchSubTaskResult<R>> rList = new ArrayList<>();
        for (T t : list) {
            rList.add(call(t));
        }
        return rList;
    }

    /**
     * 子类实现，处理单个原始数据
     *
     * @param t
     * @return
     */
    protected abstract BatchSubTaskResult<R> call(T t);

}
