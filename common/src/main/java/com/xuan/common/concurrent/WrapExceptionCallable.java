package com.xuan.common.concurrent;

import java.util.concurrent.Callable;

/**
 * @author xuan
 * @since 2021/1/26
 */
public class WrapExceptionCallable<T> implements Callable<T> {
    private Callable<T> callable;

    public WrapExceptionCallable(Callable<T> callable) {
        //如果参数为null 记录错误日志，并设置一个空任务，这样线程池可以快速结束
        if (callable == null) {
            System.out.println("Builder_WrapExceptionCallable_Callable_Is_Null");
            this.callable = () -> null;
        } else {
            this.callable = callable;
        }
    }

    @Override
    public T call() {
        try {
            return callable.call();
        } catch (Exception e) {
            System.out.println("WrapExceptionCallable_Call_Throwable");
            return null;
        }
    }

}
