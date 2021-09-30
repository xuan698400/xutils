package com.xuan.mix.domain.shop.model.businesstime;

import java.util.List;

/**
 * 常规的营业时间，一般指定一周中哪几天营业，每天营业时间段是什么
 *
 * @author xuan
 * @since 2021/9/30
 */
public class NormalBusinessTime {

    /**
     * 指定一周哪几天营业，例如工作日营业，设置值：[1,2,3,4,5]
     */
    private List<Integer> weeks;
    /**
     * 指定一天内营业时间段
     */
    private List<BusinessTimeRange> rangeList;

    public List<Integer> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<Integer> weeks) {
        this.weeks = weeks;
    }

    public List<BusinessTimeRange> getRangeList() {
        return rangeList;
    }

    public void setRangeList(List<BusinessTimeRange> rangeList) {
        this.rangeList = rangeList;
    }

}
