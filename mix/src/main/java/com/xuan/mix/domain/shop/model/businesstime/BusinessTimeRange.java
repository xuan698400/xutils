package com.xuan.mix.domain.shop.model.businesstime;

/**
 * 一天中的时间段
 *
 * @author xuan
 * @since 2021/9/30
 */
public class BusinessTimeRange {

    private HourMinute start;

    private HourMinute end;

    public HourMinute getStart() {
        return start;
    }

    public void setStart(HourMinute start) {
        this.start = start;
    }

    public HourMinute getEnd() {
        return end;
    }

    public void setEnd(HourMinute end) {
        this.end = end;
    }

}
