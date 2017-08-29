package com.xuan.xutils.concurrent.forkjoin.listtask.executor;

import com.xuan.xutils.concurrent.forkjoin.listtask.callback.ListTaskCallable;
import com.xuan.xutils.concurrent.forkjoin.listtask.config.ListTaskConfig;
import com.xuan.xutils.concurrent.forkjoin.listtask.core.ListTask;
import com.xuan.xutils.concurrent.forkjoin.listtask.core.ListTaskException;
import com.xuan.xutils.concurrent.forkjoin.listtask.core.ListTaskResult;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 把一个List任务进行分解并发执行，对使用者又可以同步拿到数据
 * <p>
 * Created by xuan on 17/8/23.
 */
public class ListTaskExecutorImpl<T, R> implements ListTaskExecutor<T, R> {
    /**
     * ForkJoin线程池，一个执行器为一个线程池。
     * 为了可控，一个应用最好使用Spring只实例化一个ListTaskExecutor实例，虽然性能上会打折扣，但是我们还是要保证资源不被耗尽而影响整个应用
     */
    private ForkJoinPool forkJoinPool;

    /**
     * 构造方法：可以线程池的数量
     *
     * @param parallelism
     */
    public ListTaskExecutorImpl(int parallelism) {
        forkJoinPool = new ForkJoinPool(parallelism);
    }

    /**
     * 构造方法：默认线程池的数量为10
     */
    public ListTaskExecutorImpl() {
        this(10);
    }

    @Override
    public ListTaskResult<R> execute(List<T> orignList, ListTaskCallable<T, R> callable, ListTaskConfig config) throws ListTaskException {
        ListTask<T, R> listTask = new ListTask<T, R>(orignList, callable, config);
        Future<ListTaskResult<R>> future = forkJoinPool.submit(listTask);
        try {
            return future.get(config.getTimeoutSecond(), TimeUnit.SECONDS);
        } catch (InterruptedException e1) {
            throw new ListTaskException(e1);
        } catch (ExecutionException e2) {
            throw new ListTaskException(e2);
        } catch (TimeoutException e3) {
            throw new ListTaskException(e3);
        }
    }

    @Override
    public List<R> execute(List<T> orignList, ListTaskCallable<T, R> callable) throws ListTaskException {
        ListTaskConfig config = new ListTaskConfig();
        config.setSubOriginListSize(1);
        return execute(orignList, callable, config).getList();
    }

    @Override
    public List<R> execute(List<T> orignList, ListTaskCallable<T, R> callable, int subOriginListSize) throws ListTaskException {
        ListTaskConfig config = new ListTaskConfig();
        config.setSubOriginListSize(subOriginListSize);
        return execute(orignList, callable, config).getList();
    }

}
