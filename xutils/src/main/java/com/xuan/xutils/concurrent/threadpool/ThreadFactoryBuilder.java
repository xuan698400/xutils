package com.xuan.xutils.concurrent.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 构建ThreadFactory工具类(来自guava)
 * <p>
 * Created by xuan on 17/8/14.
 */
public class ThreadFactoryBuilder {
    /**
     * 名称格式化串
     */
    private String nameFormat = null;

    /**
     * 是否是守护线程
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
     * 给线程设置名称,支持format
     *
     * @param nameFormat
     * @return
     */
    public ThreadFactoryBuilder setNameFormat(String nameFormat) {
        String.format(nameFormat, 0);//如果nameFormat是null或者format格式不对,就能立马返回错误
        this.nameFormat = nameFormat;
        return this;
    }

    /**
     * 设置是否守护
     *
     * @param daemon
     * @return
     */
    public ThreadFactoryBuilder setDaemon(boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    /**
     * 设置优先级
     *
     * @param priority
     * @return
     */
    public ThreadFactoryBuilder setPriority(int priority) {
        //虽然在Thread#setPriority()时会校验有限级的有效性.但是这里提前校验一下,提示效果更好,如果是无效还能提前校验出来
        checkArgument(priority >= Thread.MIN_PRIORITY, "Thread priority (%s) must be >= %s", priority, Thread.MIN_PRIORITY);
        checkArgument(priority <= Thread.MAX_PRIORITY, "Thread priority (%s) must be <= %s", priority, Thread.MAX_PRIORITY);
        this.priority = priority;
        return this;
    }

    /**
     * 设置未捕捉异常处理器
     *
     * @param uncaughtExceptionHandler
     * @return
     */
    public ThreadFactoryBuilder setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.uncaughtExceptionHandler = checkNotNull(uncaughtExceptionHandler);
        return this;
    }

    /**
     * 设置ThreadFactory
     *
     * @param backingThreadFactory
     * @return
     */
    public ThreadFactoryBuilder setThreadFactory(ThreadFactory backingThreadFactory) {
        this.backingThreadFactory = checkNotNull(backingThreadFactory);
        return this;
    }

    /**
     * 根据参数开始构建
     *
     * @return
     */
    public ThreadFactory build() {
        return build(this);
    }

    /**
     * 内部构建
     *
     * @param builder
     * @return
     */
    private static ThreadFactory build(ThreadFactoryBuilder builder) {
        final String nameFormat = builder.nameFormat;
        final Boolean daemon = builder.daemon;
        final Integer priority = builder.priority;
        final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = builder.uncaughtExceptionHandler;
        final ThreadFactory backingThreadFactory = (builder.backingThreadFactory != null) ? builder.backingThreadFactory : Executors.defaultThreadFactory();
        final AtomicLong count = (nameFormat != null) ? new AtomicLong(0) : null;
        return new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = backingThreadFactory.newThread(runnable);
                if (nameFormat != null) {
                    thread.setName(String.format(nameFormat, count.getAndIncrement()));
                }
                if (daemon != null) {
                    thread.setDaemon(daemon);
                }
                if (priority != null) {
                    thread.setPriority(priority);
                }
                if (uncaughtExceptionHandler != null) {
                    thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
                }
                return thread;
            }
        };
    }

    /**
     * expression校验
     *
     * @param expression
     * @param errorMessageTemplate
     * @param errorMessageArgs
     */
    private void checkArgument(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
        if (!expression) {
            throw new IllegalArgumentException(format(errorMessageTemplate, errorMessageArgs));
        }
    }

    /**
     * %s标识符号替换成真实值
     *
     * @param template
     * @param args
     * @return
     */
    private String format(String template, Object... args) {
        template = String.valueOf(template); // null -> "null"

        // start substituting the arguments into the '%s' placeholders
        StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
        int templateStart = 0;
        int i = 0;
        while (i < args.length) {
            int placeholderStart = template.indexOf("%s", templateStart);
            if (placeholderStart == -1) {
                break;
            }
            builder.append(template.substring(templateStart, placeholderStart));
            builder.append(args[i++]);
            templateStart = placeholderStart + 2;
        }
        builder.append(template.substring(templateStart));

        // if we run out of placeholders, append the extra args in square braces
        if (i < args.length) {
            builder.append(" [");
            builder.append(args[i++]);
            while (i < args.length) {
                builder.append(", ");
                builder.append(args[i++]);
            }
            builder.append(']');
        }

        return builder.toString();
    }

    /**
     * 判空
     *
     * @param reference
     * @param <T>
     * @return
     */
    private <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

}
