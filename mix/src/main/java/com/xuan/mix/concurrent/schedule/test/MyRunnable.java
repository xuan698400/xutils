package com.xuan.mix.concurrent.schedule.test;

import com.xuan.mix.concurrent.schedule.AbstractRunnableTask;

/**
 * @author xuan
 * @since 2021/9/13
 */
public class MyRunnable extends AbstractRunnableTask {

    public MyRunnable(String name) {
        super(name);
    }

    @Override
    protected void beforeProcessTask() {
        System.out.println("MyRunnable-beforeProcessTask");
    }

    @Override
    protected void afterProcessTask() {
        System.out.println("MyRunnable-afterProcessTask");
    }

    @Override
    public void processTask() throws Exception {
        System.out.println("MyRunnable-processTask");
    }

}
