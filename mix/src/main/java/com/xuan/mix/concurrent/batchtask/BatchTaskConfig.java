package com.xuan.mix.concurrent.batchtask;

import java.util.concurrent.ExecutorService;

/**
 * 执行时的配置参数，可以让一些高级用户定制一些参数来影响子任务的分解等
 * <p>
 *
 * @author xuan
 * @date 17/8/29
 */
public class BatchTaskConfig {

    /**
     * 在拆分子任务时，以多少个原数据为单位，默认：1
     */
    private int subOriginListSize = 1;

    /**
     * 整个任务执行超时时间，单位：毫秒，默认3秒
     */
    private long timeout = 3000;
    /**
     * 用户可以自定义线程池
     */
    private ExecutorService customExecutor;

    public int getSubOriginListSize() {
        return subOriginListSize;
    }

    public void setSubOriginListSize(int subOriginListSize) {
        this.subOriginListSize = subOriginListSize;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public ExecutorService getCustomExecutor() {
        return customExecutor;
    }

    public void setCustomExecutor(ExecutorService customExecutor) {
        this.customExecutor = customExecutor;
    }

}
