package com.xuan.xutils.bt.tracker;

/**
 * 日志打点对象
 * <p>
 * Created by xuan on 17/7/29.
 */
public class TKPoint {
    /**
     * 路径点名称
     */
    private String name;

    /**
     * 备注说明
     */
    private String remark;

    /**
     * 距离上次间隔时间,单位:毫秒
     */
    private Long interval;

    /**
     * 当前时间戳
     */
    private Long timestamp;

    public TKPoint(String name) {
        this.name = name;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 获取记录的第一点
     *
     * @param name
     * @return
     */
    public static TKPoint buildStart(String name) {
        TKPoint tkPoint = new TKPoint(name);
        tkPoint.setInterval(0L);
        return tkPoint;
    }

    /**
     * 中间的点
     *
     * @param pre
     * @param name
     * @return
     */
    public static TKPoint buildOn(TKPoint pre, String name) {
        TKPoint tkPoint = new TKPoint(name);
        if (null != pre && null != pre.getTimestamp()) {
            tkPoint.setInterval(tkPoint.getTimestamp().longValue() - pre.getTimestamp().longValue());
        }
        return tkPoint;
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
        StringBuilder sb = new StringBuilder();
        sb.append("name=" + name);
        sb.append("remark=" + remark);
        sb.append("interval=" + interval);
        sb.append("timestamp=" + timestamp);
        return sb.toString();
    }
}
