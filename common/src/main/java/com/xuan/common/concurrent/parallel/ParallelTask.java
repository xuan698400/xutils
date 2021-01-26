package com.xuan.common.concurrent.parallel;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import com.xuan.common.concurrent.ThreadLocalUtil;

/**
 * 并行任务
 *
 * @author xuan
 * @since 2021/1/26
 */
public class ParallelTask implements Runnable {

    /**
     * 并行任务计数器
     */
    private CountDownLatch countDownLatch;
    /**
     * 真正执行任务
     */
    private Runnable runnable;
    /**
     * 业务线程上下文
     */
    private Map<String, Object> bizContext;
    /**
     * 0 任务初始化
     * 1 任务运行中
     * 2 任务已完成
     * 3 任务已取消
     */
    private AtomicInteger status = new AtomicInteger(0);
    private static final int INIT = 0;
    private static final int RUNNING = 1;
    private static final int COMPLETE = 2;
    private static final int CANCELED = 3;

    public ParallelTask(CountDownLatch countDownLatch, Runnable runnable) {
        this.countDownLatch = countDownLatch;
        this.runnable = runnable;
    }

    @Override
    public void run() {
        // 将状态从 0 修改到 1
        if (!status.compareAndSet(INIT, RUNNING)) {
            return;
        }

        boolean callerRunsPolicy = false;
        try {
            //bizContext一致，说明该任务是由主线程执行的，在finally中不需要清空bizContext
            if (bizContext == ThreadLocalUtil.get()) {
                callerRunsPolicy = true;
            }
            // 主线程上下文传递到子线程
            ThreadLocalUtil.set(bizContext);

            runnable.run();
        } catch (Exception e) {
            //单个任务执行错误，这里捕获输出日志
            System.out.println("ParallelTask_Run_Exception.");
        } finally {
            //每执行一次一定要把计数器减一次
            if (status.compareAndSet(RUNNING, COMPLETE)) {
                countDownLatch.countDown();
            }
            if (!callerRunsPolicy) {
                // 线程执行完清除上下文，避免线程间串上下文
                ThreadLocalUtil.remove();
            }
        }
    }

    public void setBizContext(Map<String, Object> bizContext) {
        this.bizContext = bizContext;
    }

    /**
     * 取消任务
     *
     * @return true 取消成功 | false 任务取消失败，可能是正在执行、执行完成或者已经取消
     */
    public boolean cancel() {
        boolean compareAndSet = status.compareAndSet(INIT, CANCELED);
        if (compareAndSet) {
            countDownLatch.countDown();
        }
        return compareAndSet;
    }

    /**
     * 任务是否已经取消
     *
     * @return true 已经取消 | false 未取消
     */
    public boolean isCanceled() {
        return status.get() == 3;
    }

    /**
     * 任务是否已经完成
     *
     * @return true 已经完成 | false 未完成
     */
    public boolean isDone() {
        return status.get() == 2;
    }

}
