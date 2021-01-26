package com.xuan.common.concurrent;

/**
 * 保证不会有Exception抛出到线程池的Runnable，防止用户没有捕捉异常导致中断了线程池中的线程。
 *
 * @author xuan
 * @since 2021/1/26
 */
public class WrapExceptionRunnable implements Runnable {

    private Runnable runnable;

    public WrapExceptionRunnable(Runnable runnable) {
        //如果参数为null 记录错误日志，并设置一个空任务，这样线程池可以快速结束
        if (runnable == null) {
            System.out.println("Builder_WrapExceptionRunnable_Runnable_Is_Null.");
            this.runnable = () -> {
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
            System.out.println("WrapExceptionRunnable_Run_Throwable.");
        }
    }

}
