package com.xuan.xutils.concurrent.forkjoin.listtask.config;

import java.util.concurrent.TimeUnit;

/**
 * 执行时的配置参数，可以让一些高级用户定制一些参数来影响子任务的分解等
 * <p>
 * Created by xuan on 17/8/26.
 */
public class ListTaskConfig {

    /**
     * 默认以1个原数据拆分成一个子任务
     */
    private final static int DEFAULT_SUB_ORIGIN_LIST_SIZE = 1;

    /**
     * 超时默认：5秒
     */
    private final static int DEFAULT_TIMEOUT_SECOND = 5;

    /**
     * 在拆分子任务时，以多少个原数据为单位，默认：1
     */
    private int subOriginListSize = DEFAULT_SUB_ORIGIN_LIST_SIZE;

    /**
     * 任务执行超时时间，单位：秒
     */
    private long timeoutSecond = DEFAULT_TIMEOUT_SECOND;

    public int getSubOriginListSize() {
        return subOriginListSize;
    }

    public void setSubOriginListSize(int subOriginListSize) {
        this.subOriginListSize = subOriginListSize;
    }

    public long getTimeoutSecond() {
        return timeoutSecond;
    }

    public void setTimeoutSecond(long timeoutSecond) {
        this.timeoutSecond = timeoutSecond;
    }

}
