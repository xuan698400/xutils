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
     * 回调，入参是需要处理的数据，返回是处理后的数据
     *
     * @param subList 切割子列表
     * @return 处理结果
     */
    List<BatchSubTaskResult<R>> call(List<T> subList);
}
