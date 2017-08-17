package com.xuan.xutils.bt.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * 日志打点,整个链路
 * <p>
 * Created by xuan on 17/7/29.
 */
public class TKPointLine {

    /**
     * 从开始到结束记录的打点日志
     */
    private List<TKPoint> pointList = new ArrayList<>();

    public List<TKPoint> getPointList() {
        return pointList;
    }

    public void setPointList(List<TKPoint> pointList) {
        this.pointList = pointList;
    }

    /**
     * 添加一个日志点
     *
     * @param tkPoint
     */
    public void addPoint(TKPoint tkPoint) {
        this.pointList.add(tkPoint);
    }

}
