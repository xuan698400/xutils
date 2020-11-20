package com.xuan.mix.bt.tracker;

/**
 * 路径节点名称模型
 * <p>
 * Created by xuan on 17/7/29.
 */
public class TkPoint {
    /**
     * 路径节点名称
     */
    private String name;

    /**
     * 路径节点备注说明，可以放一些必要参数，后面做日志分析
     */
    private String remark;
    /**
     * 距离上次路径节点间隔时间，单位:毫秒
     */
    private Long interval;
    /**
     * 当前时间戳
     */
    private Long timestamp;

    TkPoint(String name) {
        this.name = name;
        this.timestamp = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getInterval() {
        return interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "name=" + name + "&remark=" + remark + "&interval=" + interval + "&timestamp=" + timestamp;
    }

}
