package com.xuan.mix.domain.shop.model.businesstime;

import java.util.Date;
import java.util.List;

/**
 * 特殊时间段营业时间单独定制，例如每当假期时，营业时间和平时是不一样的
 *
 * @author xuan
 * @since 2021/9/30
 */
public class SpecialBusinessTime {

    /**
     * 开始时间，精度到天
     */
    private Date startDay;
    /**
     * 结束时间，精度到天
     */
    private Date endDay;

    /**
     * 指定一天内营业时间段
     */
    private List<BusinessTimeRange> rangeList;

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public List<BusinessTimeRange> getRangeList() {
        return rangeList;
    }

    public void setRangeList(List<BusinessTimeRange> rangeList) {
        this.rangeList = rangeList;
    }

}
