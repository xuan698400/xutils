package com.xuan.xutils.concurrent.schedule;

import java.util.Date;

/**
 * 描述某个系统任务的抽象基类.
 *
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:26:19 $
 */
public abstract class AbstractTask {

    /**
     * 任务名称
     */
    private final String name;

    /**
     * 执行时间戳
     */
    private final Date timestamp;

    /**
     * 是否正在执行
     */
    protected volatile boolean isWorking = true;

    public AbstractTask(String name) {
        this.name = name;
        this.timestamp = new Date();
    }

    public String getName() {
        return name;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    public boolean isWorking() {
        return isWorking;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append("[");
        sb.append("name=").append(name).append(",");
        sb.append("isWorking=").append(isWorking).append(",");
        sb.append("timestamp=").append(timestamp).append("]");
        return sb.toString();
    }

}
