package com.xuan.common.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 构建ThreadFactory工具类
 *
 * @author xuan
 * @since 2021/1/26
 */
public class ThreadFactoryBuilder {
    /**
     * 名称格式化串
     */
    private String nameFormat = null;
    /**
     * 是否后台线程，所谓后台线程是指这个线程并不属于程序中不可或缺的部分。因此，当所有的非后台线程结束时，程序也就终止了
     */
    private Boolean daemon = null;
    /**
     * 线程优先级
     */
    private Integer priority = null;
    /**
     * 线程发生异常后,未正常捕获时,会回调这个异常处理器
     */
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler = null;
    /**
     * 线程工厂
     */
    private ThreadFactory backingThreadFactory = null;

    public ThreadFactoryBuilder() {
    }

    /**
     * 设置线程名称的格式
     *
     * @param nameFormat 格式例如：bizXxx-pool-%d，其中%d就是线程编号
     * @return ThreadFactoryBuilder
     */
    public ThreadFactoryBuilder setNameFormat(String nameFormat) {
        //如果nameFormat是null或者format格式不对,就能立马返回错误
        String.format(nameFormat, 0);
        this.nameFormat = nameFormat;
        return this;
    }

    public ThreadFactoryBuilder setDaemon(boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    public ThreadFactoryBuilder setPriority(int priority) {
        //虽然在Thread#setPriority()时会校验有限级的有效性.但是这里提前校验一下,提示效果更好,如果是无效还能提前校验出来
        if (priority < Thread.MIN_PRIORITY) {
            throw new IllegalArgumentException(
                String.format("Thread priority (%s) must be >= %s", priority, Thread.MIN_PRIORITY));
        }

        if (priority > Thread.MAX_PRIORITY) {
            throw new IllegalArgumentException(
                String.format("Thread priority (%s) must be <= %s", priority, Thread.MAX_PRIORITY));
        }

        this.priority = priority;
        return this;
    }

    public ThreadFactoryBuilder setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        if (null == uncaughtExceptionHandler) {
            throw new NullPointerException();
        }
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
        return this;
    }

    public ThreadFactoryBuilder setThreadFactory(ThreadFactory backingThreadFactory) {
        if (null == backingThreadFactory) {
            throw new NullPointerException();
        }
        this.backingThreadFactory = backingThreadFactory;
        return this;
    }

    public ThreadFactory build() {
        return build(this);
    }

    private static ThreadFactory build(ThreadFactoryBuilder builder) {
        final String nameFormat = builder.nameFormat;
        final Boolean daemon = builder.daemon;
        final Integer priority = builder.priority;
        final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = builder.uncaughtExceptionHandler;
        final ThreadFactory backingThreadFactory = (null != builder.backingThreadFactory) ? builder.backingThreadFactory
            : Executors.defaultThreadFactory();
        final AtomicLong count = (nameFormat != null) ? new AtomicLong(0) : null;

        return (runnable) -> {
            Thread thread = backingThreadFactory.newThread(runnable);
            if (null != nameFormat) {
                thread.setName(String.format(nameFormat, count.getAndIncrement()));
            }
            if (null != daemon) {
                thread.setDaemon(daemon);
            }
            if (priority != null) {
                thread.setPriority(priority);
            }
            if (uncaughtExceptionHandler != null) {
                thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
            }
            return thread;
        };
    }

}
