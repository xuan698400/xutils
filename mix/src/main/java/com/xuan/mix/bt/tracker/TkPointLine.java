package com.xuan.mix.bt.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * 日志打点,整个链路模型
 * <p>
 * Created by xuan on 17/7/29.
 */
public class TkPointLine {

    /**
     * 从开始到结束记录的打点日志
     */
    private List<TkPoint> pointList;

    public List<TkPoint> getPointList() {
        return pointList;
    }

    public void setPointList(List<TkPoint> pointList) {
        this.pointList = pointList;
    }

    /**
     * 添加一个日志点
     *
     * @param tkPoint TkPoint
     */
    public void addPoint(TkPoint tkPoint) {
        if (null == pointList) {
            pointList = new ArrayList<>();
        }

        pointList.add(tkPoint);
    }

}
