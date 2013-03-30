package com.xuan.utils.concurrent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务执行器。一些比较耗时的任务可以通过提交到此类来异步执行。
 * 
 * <p>
 * 在内部实现上使用了一个固定长度的线程池和一个无界的请求队列来执行提交的任务。
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:32:42 $
 */
public class ScheduledTaskExecutor extends ScheduledThreadPoolExecutor {

    // 活动的(正在执行的)服务对象列表
    private final List<AbstractTask> activeTasks = new CopyOnWriteArrayList<AbstractTask>();

    // 所有提交的服务对象列表
    private final List<AbstractTask> allTasks = new CopyOnWriteArrayList<AbstractTask>();

    /**
     * 使用给定的核心池大小创建一个新的 ScheduledTaskExecutor。
     * 
     * @param corePoolSize
     *            线程池中所保留的线程数（包括空闲线程）
     * @throws IllegalArgumentException
     *             如果 <tt>corePoolSize &lt; 0</tt>
     */
    public ScheduledTaskExecutor(int corePoolSize) {
        super(corePoolSize);
    }

    /**
     * 使用给定的核心池大小创建一个新的 ScheduledTaskExecutor。
     * 
     * @param corePoolSize
     *            线程池中所保留的线程数（包括空闲线程）
     * @param threadFactory
     *            执行程序创建新线程时使用的工厂
     * @throws IllegalArgumentException
     *             如果 <tt>corePoolSize &lt; 0</tt>
     * @throws NullPointerException
     *             如果 threadFactory 为null
     */
    public ScheduledTaskExecutor(int corePoolSize, ThreadFactory threadFactory) {
        super(corePoolSize, threadFactory);
    }

    /**
     * 使用给定的核心池大小创建一个新的 ScheduledTaskExecutor。
     * 
     * @param corePoolSize
     *            线程池中所保留的线程数（包括空闲线程）
     * @param handler
     *            由于超出线程范围和队列容量而使执行被阻塞时所使用的处理程序
     * @throws IllegalArgumentException
     *             如果 <tt>corePoolSize &lt; 0</tt>
     * @throws NullPointerException
     *             如果 handler 为null
     */
    public ScheduledTaskExecutor(int corePoolSize, RejectedExecutionHandler handler) {
        super(corePoolSize, handler);
    }

    /**
     * 使用给定的核心池大小创建一个新的 ScheduledTaskExecutor。
     * 
     * @param corePoolSize
     *            线程池中所保留的线程数（包括空闲线程）
     * @param threadFactory
     *            执行程序创建新线程时使用的工厂
     * @param handler
     *            由于超出线程范围和队列容量而使执行被阻塞时所使用的处理程序
     * @throws IllegalArgumentException
     *             如果 <tt>corePoolSize &lt; 0</tt>
     * @throws NullPointerException
     *             如果 threadFactory 或者 handler 为null
     */
    public ScheduledTaskExecutor(int corePoolSize, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, threadFactory, handler);
    }

    /**
     * 使用所要求的零延迟执行命令。这在效果上等同于调用 schedule(task, 0, anyUnit)。
     * 
     * @param task
     *            要执行的任务
     */
    public void execute(AbstractRunnableTask task) {
        super.execute(task);
        addTask(task);
    }

    /**
     * 提交一个带执行结果的任务.
     * 
     * @param <T>
     *            任务结果类型
     * @param task
     *            要执行的任务
     * @return 任务的执行结果
     */
    public <T> Future<T> submit(AbstractCallableTask<T> task) {
        Future<T> future = null;
        try {
            future = super.submit(task);
            addTask(task);
        }
        catch (RuntimeException e) {
            throw e;
        }
        return future;
    }

    /**
     * 创建并执行在给定延迟时间后启用的一次性任务。
     * 
     * @param task
     *            要执行的任务
     * @param delay
     *            从现在开始延迟执行的时间
     * @param unit
     *            延迟参数的时间单位
     * @return 表示挂起任务完成的 ScheduledFuture，并且其 get() 方法在完成后将返回 null。
     */
    public ScheduledFuture<?> schedule(AbstractRunnableTask task, long delay, TimeUnit unit) {
        ScheduledFuture<?> future = null;
        try {
            future = super.schedule(task, delay, unit);
            addTask(task);
        }
        catch (RuntimeException e) {
            throw e;
        }
        return future;
    }

    /**
     * 创建并执行一个在给定初始延迟后首次启用的定期操作，后续操作具有给定的周期；<br>
     * 也就是将在 initialDelay 后开始执行，然后在 initialDelay + period 后执行，接着在 initialDelay + 2 * period 后执行，依此类推。<br>
     * 如果任务的任一执行遇到异常，都会取消后续执行。否则，只能通过执行程序的取消或终止方法来终止该任务。
     * 
     * @param task
     *            要执行的任务
     * @param initialDelay
     *            首次执行的延迟时间
     * @param period
     *            连续执行之间的周期
     * @param unit
     *            initialDelay 和 period 参数的时间单位
     * @return 表示挂起任务完成的 Future，并且其 get() 方法在取消后将抛出异常。
     * @throws RejectedExecutionException
     *             如果无法安排执行该任务
     * @throws NullPointerException
     *             如果 command 为 null
     * @throws IllegalArgumentException
     *             如果 period 小于或等于 0
     */
    public ScheduledFuture<?> scheduleAtFixedRate(AbstractRunnableTask task, long initialDelay, long period,
            TimeUnit unit) {
        ScheduledFuture<?> future = null;
        try {
            future = super.scheduleAtFixedRate(task, initialDelay, period, unit);
            addTask(task);
        }
        catch (RuntimeException e) {
            throw e;
        }
        return future;
    }

    /**
     * 创建并执行一个在给定初始延迟后首次启用的定期操作，随后，在每一次执行终止和下一次执行开始之间都存在给定的延迟。<br>
     * 如果任务的任一执行遇到异常，就会取消后续执行。否则，只能通过执行程序的取消或终止方法来终止该任务。
     * 
     * @param task
     *            要执行的任务
     * @param initialDelay
     *            首次执行的延迟时间
     * @param delay
     *            一次执行终止和下一次执行开始之间的延迟
     * @param unit
     *            initialDelay 和 delay 参数的时间单位
     * @return 表示挂起任务完成的 Future，并且其 get() 方法在取消后将抛出异常。
     * @throws RejectedExecutionException
     *             如果无法安排执行该任务
     * @throws NullPointerException
     *             如果 command 为 null
     * @throws IllegalArgumentException
     *             如果 delay 小于或等于 0
     */
    public ScheduledFuture<?> scheduleWithFixedDelay(AbstractRunnableTask task, long initialDelay, long delay,
            TimeUnit unit) {
        ScheduledFuture<?> future = null;
        try {
            future = super.scheduleWithFixedDelay(task, initialDelay, delay, unit);
            addTask(task);
        }
        catch (RuntimeException e) {
            throw e;
        }
        return future;
    }

    /**
     * 获取所有已提交的任务.
     * 
     * @return 所有已提交的任务列表
     */
    public List<AbstractTask> getAllTasks() {
        return Collections.unmodifiableList(allTasks);
    }

    /**
     * 获取正在执行的任务.
     * 
     * @return 正在执行的任务列表
     */
    public List<AbstractTask> getActiveTasks() {
        return Collections.unmodifiableList(activeTasks);
    }

    /**
     * 在以前已提交任务的执行中发起一个有序的关闭，但是不接受新任务。<br>
     * 如果已将 ExecuteExistingDelayedTasksAfterShutdownPolicy 设置为 false，则取消尚未超出其延迟的现有延迟任务。<br>
     * 并且除非已将 ContinueExistingPeriodicTasksAfterShutdownPolicy 设置为 true，否则将取消现有定期任务的后续执行。
     */
    @Override
    public void shutdown() {
        clearTasks();
        super.shutdown();
    }

    /**
     * 尝试停止所有正在执行的任务、暂停等待任务的处理，并返回等待执行的任务列表。<br>
     * 虽然尽最大努力，但并不保证可以停止处理正在执行的任务。<br>
     * 此实现通过 Thread.interrupt() 取消任务，所以任何无法响应中断的任务都可能永远无法终止。
     */
    @Override
    public List<Runnable> shutdownNow() {
        clearTasks();
        return super.shutdownNow();
    }

    @Override
    protected void terminated() {
        super.terminated();
    }

    /**
     * 修改或替换用于执行 runnable 的任务。此方法可重写用于管理内部任务的具体类。默认实现只返回给定任务。<br>
     * <b>注意：对于定期反复执行的任务，JDK6.0有一个在任务执行一次之后返回的给定任务就不会经过此方法包装的bug。</b>
     * 
     * @param r
     *            所提交的 Runnable
     * @param task
     *            执行 runnable 所创建的任务
     * @return 可以执行 runnable 的任务
     */
    @Override
    protected <V> RunnableScheduledFuture<V> decorateTask(Runnable r, RunnableScheduledFuture<V> task) {
        return new NamedScheduledFuture<V>((AbstractRunnableTask) r, task);
    }

    /**
     * 修改或替换用于执行 callable 的任务。此方法可重写用于管理内部任务的具体类。默认实现只返回给定任务。<br>
     * <b>注意：对于定期反复执行的任务，JDK6.0有一个在任务执行一次之后返回的给定任务就不会经过此方法包装的bug。</b>
     * 
     * @param c
     *            所提交的 Callable
     * @param task
     *            执行 callable 所创建的任务
     * @return 可以执行 callable 的任务
     */
    @Override
    protected <V> RunnableScheduledFuture<V> decorateTask(Callable<V> c, RunnableScheduledFuture<V> task) {
        return new NamedScheduledFuture<V>((AbstractCallableTask<V>) c, task);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);

        if (!(r instanceof NamedScheduledFuture<?>)) {
            return;
        }

        AbstractTask task = ((NamedScheduledFuture<?>) r).getTask();

        activeTasks.add(task);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
            if (r instanceof NamedScheduledFuture<?>) {
                AbstractTask task = ((NamedScheduledFuture<?>) r).getTask();

                activeTasks.remove(task);

                allTasks.remove(task);
            }
        }
        finally {
            super.afterExecute(r, t);
        }
    }

    /**
     * 添加一个任务到所有任务列表
     * 
     * @param task
     */
    private void addTask(AbstractTask task) {
        allTasks.add(task);
    }

    /**
     * 清除所有任务和活跃任务列表
     */
    private void clearTasks() {
        activeTasks.clear();

        allTasks.clear();
    }

}
