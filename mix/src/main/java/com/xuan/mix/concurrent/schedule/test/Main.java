package com.xuan.mix.concurrent.schedule.test;

import java.util.concurrent.TimeUnit;

import com.xuan.mix.concurrent.schedule.ScheduledTaskExecutor;

/**
 * @author xuan
 * @since 2021/9/13
 */
public class Main {

    private static ScheduledTaskExecutor scheduledTaskExecutor = new ScheduledTaskExecutor(10);

    public static void main(String[] args) {

        scheduledTaskExecutor.scheduleAtFixedRate(new MyRunnable("ddd"), 3, 1, TimeUnit.SECONDS);
    }
}
