package com.xuan.xutils.concurrent.forkjoin.listtask.config;

/**
 * 配置参数,后期可以用这个配置，让用户定制一些参数
 * <p>
 * Created by xuan on 17/8/26.
 */
public class ListTaskConfig {
    private final static int DEFAULT_SUB_ORIGIN_LIST_SIZE = 1;

    /**
     * 以多少个单位为一个小任务，默认：1
     */
    private int subOriginListSize = DEFAULT_SUB_ORIGIN_LIST_SIZE;

    public int getSubOriginListSize() {
        return subOriginListSize;
    }

    public void setSubOriginListSize(int subOriginListSize) {
        this.subOriginListSize = subOriginListSize;
    }

}
