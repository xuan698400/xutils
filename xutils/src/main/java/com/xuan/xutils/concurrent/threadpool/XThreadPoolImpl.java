package com.xuan.xutils.concurrent.threadpool;

import com.xuan.xutils.utils.StringUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 通用配置实现(还有待进一步整理)
 * <p>
 * Created by xuan on 17/8/14.
 */
public class XThreadPoolImpl implements XThreadPool {

    /**
     * 线程池名称
     */
    private String name;

    /**
     * 内部核心执行服务
     */
    private ExecutorService executor;

    /**
     * 当关闭线程池时是否关闭任务
     */
    private boolean waitForTasksToCompleteOnShutdown = false;

    /**
     * 阻塞队列长度
     */
    private int queueCapacity = 1024;

    /**
     * 核心线程数，这个是肯定会分配的线程数
     */
    private int corePoolSize = 10;

    /**
     * 最大线程数，实际上能否达到还取决于队列数queueCapacity是否满了
     */
    private int maxPoolSize = 20;

    /**
     * 空闲线程的存活时间，为0表示一直可以存活
     */
    private int keepAliveSeconds = 300;

    /**
     * 中断超时时间，默认5秒
     */
    private int shutdownTimeout = 5000;

    /**
     * 默认的拒绝策略
     */
    private RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();

    /**
     * 设置线程池名称
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 如果配置了等待任务完成那么先使用shutdown, 停止接收新任务并尝试完成所有已存在任务.否则直接调用shutdownNow 取消所有workQueue中Pending的任务，并中断所有阻塞函数。
     *
     * @throws Exception
     */
    public void destroy() throws Exception {
        if (this.waitForTasksToCompleteOnShutdown) {
            this.executor.shutdown();
        } else {
            this.executor.shutdownNow();
        }
        awaitTerminationIfNecessary();
    }

    /**
     * 等待关闭
     */
    private void awaitTerminationIfNecessary() {
        if (this.shutdownTimeout > 0) {
            try {
                if (!this.executor.awaitTermination(this.shutdownTimeout, TimeUnit.SECONDS)) {
                    System.out.println("Timed out while waiting for executor" + (this.name != null ? " '" + this.name + "'" : "") + " to terminate");
                }
            } catch (InterruptedException ex) {
                System.out.println("Interrupted while waiting for executor" + (this.name != null ? " '" + this.name + "'" : "") + " to terminate");
                // (Re-)Cancel if current thread also interrupted
                this.executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 初始化
     *
     * @throws Exception
     */
    public void init() throws Exception {
        if (StringUtils.isBlank(name)) {
            name = this.getClass().getName();
        }

        /*
         * 能定制Thread name的ThreadFactory 非常重要，使得创建的线程有自己的名字而不是默认的"pool-x-thread-y"，
         * 在用threaddump查看线程时特别有用。 格式如: "X-Thread-xxx-%d"
         */
        String threadNameFormat = "X-Thread-" + name + "-%d";
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(threadNameFormat).build();
        this.executor = initializeExecutor(threadFactory, rejectedExecutionHandler);
    }

    /**
     * 初始化线程池
     *
     * @param threadFactory
     * @param rejectedExecutionHandler
     * @return
     */
    protected ExecutorService initializeExecutor(ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        BlockingQueue<Runnable> queue = createQueue(this.queueCapacity);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(this.corePoolSize, this.maxPoolSize, this.keepAliveSeconds, TimeUnit.SECONDS, queue, threadFactory, rejectedExecutionHandler);
        return executor;
    }

    /**
     * 创建一个有界队列
     *
     * @param queueCapacity
     * @return
     */
    protected BlockingQueue<Runnable> createQueue(int queueCapacity) {
        if (queueCapacity > 0) {
            return new LinkedBlockingQueue<Runnable>(queueCapacity);
        } else {
            //一个没有数据缓冲的队列,消费者要get,必须等待生产者put,反之亦然
            return new SynchronousQueue<Runnable>();
        }
    }

    @Override
    public void execute(Runnable task) {
        ExecutorService executor = getExecutor();
        try {
            executor.execute(new WrapExceptionRunnable(task));
        } catch (RejectedExecutionException e) {
            throw new RejectedExecutionException("Executor [" + executor + "] did not accept task: " + task, e);
        }
    }

    @Override
    public Future<?> submit(Runnable task) {
        ExecutorService executor = getExecutor();
        try {
            return executor.submit(new WrapExceptionRunnable(task));
        } catch (RejectedExecutionException ex) {
            throw new RejectedExecutionException("Executor [" + executor + "] did not accept task: " + task, ex);
        }
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        ExecutorService executor = getExecutor();
        try {
            return executor.submit(new WrapExceptionCallable(task));
        } catch (RejectedExecutionException ex) {
            throw new RejectedExecutionException("Executor [" + executor + "] did not accept task: " + task, ex);
        }

    }

    /**
     * 保证不会有Exception抛出到线程池的Runnable，防止用户没有捕捉异常导致中断了线程池中的线程。
     */
    public static class WrapExceptionRunnable implements Runnable {
        private Runnable runnable;

        public WrapExceptionRunnable(Runnable runnable) {
            //如果参数为null 记录错误日志，并设置一个空任务，这样线程池可以快速结束
            if (runnable == null) {
                System.out.println("[WrapExceptionRunnable-WrapExceptionRunnable]error, runnable cann't be null");
                this.runnable = new Runnable() {
                    @Override
                    public void run() {
                    }
                };

            } else {
                this.runnable = runnable;
            }

        }

        @Override
        public void run() {
            try {
                runnable.run();
            } catch (Throwable e) {
                // catch any exception, because the scheduled thread will break if the exception thrown outside.
                System.out.println("[WrapExceptionRunnable-run] Unexpected error occurred in task" + e.getMessage());
            }
        }
    }

    /**
     * 保证不会有Exception抛出到线程池的Runnable，防止用户没有捕捉异常导致中断了线程池中的线程。
     */
    public static class WrapExceptionCallable<T> implements Callable<T> {
        private Callable<T> callable;

        public WrapExceptionCallable(Callable<T> callable) {
            //如果参数为null 记录错误日志，并设置一个空任务，这样线程池可以快速结束
            if (callable == null) {
                System.out.println("[WrapExceptionCallable-WrapExceptionCallable]error, callable cann't be null");
                this.callable = new Callable<T>() {
                    @Override
                    public T call() throws Exception {
                        return null;
                    }
                };
            } else {
                this.callable = callable;
            }
        }

        @Override
        public T call() throws Exception {
            try {
                return callable.call();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("[WrapExceptionCallable-call] Unexpected error occurred in task" + e.getMessage());
                return null;
            }
        }
    }

    //====================set and get====================

    /**
     * 设置是否等待任务结束才关闭
     *
     * @param waitForTasksToCompleteOnShutdown
     */
    public void setWaitForTasksToCompleteOnShutdown(boolean waitForTasksToCompleteOnShutdown) {
        this.waitForTasksToCompleteOnShutdown = waitForTasksToCompleteOnShutdown;
    }

    /**
     * 设置关闭超时
     *
     * @param shutdownTimeout
     */
    public void setShutdownTimeout(int shutdownTimeout) {
        this.shutdownTimeout = shutdownTimeout;
    }

    /**
     * 默认是拒绝策略
     *
     * @param rejectedExecutionHandler
     */
    public void setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
        this.rejectedExecutionHandler = (rejectedExecutionHandler != null ? rejectedExecutionHandler : new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 设置队列容量
     *
     * @param queueCapacity
     */
    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    /**
     * 设置核心线程数
     *
     * @param corePoolSize
     */
    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    /**
     * 设置最大线程数
     *
     * @param maxPoolSize
     */
    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    /**
     * 设置线程保活秒
     *
     * @param keepAliveSeconds
     */
    public void setKeepAliveSeconds(int keepAliveSeconds) {
        this.keepAliveSeconds = keepAliveSeconds;
    }

    /**
     * 获取执行器
     *
     * @return
     */
    public ExecutorService getExecutor() {
        if (this.executor != null) {
            throw new RuntimeException("executor not initialized");
        }
        return executor;
    }

}
