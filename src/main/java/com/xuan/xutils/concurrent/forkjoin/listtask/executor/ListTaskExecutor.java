package com.xuan.xutils.concurrent.forkjoin.listtask.executor;

import com.xuan.xutils.concurrent.forkjoin.listtask.callback.ListTaskCallable;
import com.xuan.xutils.concurrent.forkjoin.listtask.config.ListTaskConfig;
import com.xuan.xutils.concurrent.forkjoin.listtask.core.ListTaskResult;

import java.util.List;

/**
 * 用户入参一个List原始数据，并给出原始数据的处理回调，框架会根据配置，进行任务分解，然后并行执行，最终合并结果返回给用户
 * <p>
 * Created by xuan on 17/8/23.
 */
public interface ListTaskExecutor<T, R> {

    /**
     * 高级玩家可使用该API，进行一些配置，也会返回一些更多的信息
     *
     * @param orignList 需要并行处理的原始数据List
     * @param callable  具体处理原始数据的回调
     * @param config    一些定制配置参数，高级用户使用，小白用户可以使用下面提供的便捷方法
     * @return
     */
    ListTaskResult<R> execute(List<T> orignList, ListTaskCallable<T, R> callable, ListTaskConfig config);

    /**
     * 小白玩家可使用该API，只需要原始数据List和处理回调就可以
     *
     * @param orignList 需要并行处理的原始数据List
     * @param callable  具体处理原始数据的回调
     * @return
     */
    List<R> execute(List<T> orignList, ListTaskCallable<T, R> callable);

    /**
     * 小白玩家可使用该API，只需要原始数据List和处理回调就可以（还可以简单的设定拆分小任务的粒度）
     *
     * @param orignList         需要并行处理的原始数据List，并行执行
     * @param callable          具体处理原始数据的回调
     * @param subOriginListSize 设定多少个原始数据合并为一个小任务
     * @return
     */
    List<R> execute(List<T> orignList, ListTaskCallable<T, R> callable, int subOriginListSize);

}
