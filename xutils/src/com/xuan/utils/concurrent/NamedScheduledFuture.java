package com.xuan.utils.concurrent;

import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 带有名字修饰的定时任务描述类
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:31:00 $
 */
public class NamedScheduledFuture<V> implements RunnableScheduledFuture<V> {

    private final String name;
    private final AbstractTask task;
    private final RunnableScheduledFuture<V> future;

    public NamedScheduledFuture(AbstractTask task, RunnableScheduledFuture<V> future) {
        this.name = task.getName();
        this.task = task;
        this.future = future;
    }

    public String getName() {
        return name;
    }

    public AbstractTask getTask() {
        return task;
    }

    @Override
    public boolean isPeriodic() {
        return future.isPeriodic();
    }

    @Override
    public void run() {
        future.run();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return future.cancel(mayInterruptIfRunning);
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return future.get();
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return future.get(timeout, unit);
    }

    @Override
    public boolean isCancelled() {
        return future.isCancelled();
    }

    @Override
    public boolean isDone() {
        return future.isDone();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return future.getDelay(unit);
    }

    @Override
    public int compareTo(Delayed o) {
        return future.compareTo(o);
    }

}
