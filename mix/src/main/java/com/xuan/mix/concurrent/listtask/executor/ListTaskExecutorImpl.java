package com.xuan.mix.concurrent.listtask.executor;

import com.xuan.mix.concurrent.listtask.callback.ListTaskCallable;
import com.xuan.mix.concurrent.listtask.config.ListTaskConfig;
import com.xuan.mix.concurrent.listtask.core.ListTask;
import com.xuan.mix.concurrent.listtask.core.ListTaskException;
import com.xuan.mix.concurrent.listtask.core.ListTaskResult;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 把一个List任务进行分解并发执行，对使用者又可以同步拿到数据
 * <p>
 *
 * @author xuan
 * @date 17/8/29
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
     * @param parallelism 线程池并发数
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
    public ListTaskResult<R> execute(List<T> originList, ListTaskCallable<T, R> callable, ListTaskConfig config)
        throws ListTaskException {
        ListTask<T, R> listTask = new ListTask<>(originList, callable, config);
        Future<ListTaskResult<R>> future = forkJoinPool.submit(listTask);
        try {
            return future.get(config.getTimeoutSecond(), TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new ListTaskException(e);
        }
    }

    @Override
    public List<R> execute(List<T> originList, ListTaskCallable<T, R> callable) throws ListTaskException {
        ListTaskConfig config = new ListTaskConfig();
        config.setSubOriginListSize(1);
        return execute(originList, callable, config).getList();
    }

    @Override
    public List<R> execute(List<T> orignList, ListTaskCallable<T, R> callable, int subOriginListSize)
        throws ListTaskException {
        ListTaskConfig config = new ListTaskConfig();
        config.setSubOriginListSize(subOriginListSize);
        return execute(orignList, callable, config).getList();
    }

}
