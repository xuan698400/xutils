package com.xuan.mix.concurrent.batchtask.executor.parallel;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.xuan.mix.concurrent.batchtask.BatchSubTaskResult;

/**
 * @author xuan
 * @since 2021/9/10
 */
public class Wrapper<R> {

    private List<ParallelTask> subTaskList;

    private List<BatchSubTaskResult<R>> subTaskResultList;

    private CountDownLatch countDownLatch;

    public List<ParallelTask> getSubTaskList() {
        return subTaskList;
    }

    public void setSubTaskList(List<ParallelTask> subTaskList) {
        this.subTaskList = subTaskList;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public List<BatchSubTaskResult<R>> getSubTaskResultList() {
        return subTaskResultList;
    }

    public void setSubTaskResultList(List<BatchSubTaskResult<R>> subTaskResultList) {
        this.subTaskResultList = subTaskResultList;
    }

}
