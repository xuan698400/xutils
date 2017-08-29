package com.xuan.xutils.concurrent.forkjoin.listtask.executor;

import com.xuan.xutils.concurrent.forkjoin.listtask.callback.ListTaskCallable;
import com.xuan.xutils.concurrent.forkjoin.listtask.config.ListTaskConfig;
import com.xuan.xutils.concurrent.forkjoin.listtask.core.ListTaskException;
import com.xuan.xutils.concurrent.forkjoin.listtask.core.ListTaskResult;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 串行任务并行化执行器
 * 用户入参一个list作为原始数据，并给出原始数据的处理逻辑回调，框架会根据配置，进行任务分解，然后并行执行，最终合并结果返回给用户
 * <p>
 * Created by xuan on 17/8/23.
 */
public interface ListTaskExecutor<T, R> {

    /**
     * 高级玩家可使用该API，进行一些高级配置，也会返回一些更多的信息
     *
     * @param orignList 需要并行处理的原始数据List
     * @param callable  具体处理原始数据的回调
     * @param config    一些定制配置参数，高级用户使用，小白用户可以使用下面提供的便捷方法
     * @return
     * @throws ListTaskException 用户需要自己处理异常
     */
    ListTaskResult<R> execute(List<T> orignList, ListTaskCallable<T, R> callable, ListTaskConfig config) throws ListTaskException;

    /**
     * 小白玩家可使用该API，只需要原始数据List和处理回调就可以
     *
     * @param orignList 需要并行处理的原始数据List
     * @param callable  具体处理原始数据的回调
     * @return
     * @throws ListTaskException 用户需要自己处理异常
     */
    List<R> execute(List<T> orignList, ListTaskCallable<T, R> callable) throws ListTaskException;

    /**
     * 小白玩家可使用该API，只需要原始数据List和处理回调就可以（还可以简单的设定拆分小任务的粒度）
     *
     * @param orignList         需要并行处理的原始数据List，并行执行
     * @param callable          具体处理原始数据的回调
     * @param subOriginListSize 设定多少个原始数据为一个子任务
     * @return
     * @throws ListTaskException 用户需要自己处理异常
     */
    List<R> execute(List<T> orignList, ListTaskCallable<T, R> callable, int subOriginListSize) throws ListTaskException;

}
