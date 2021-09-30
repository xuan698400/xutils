package com.xuan.mix.domain.shop.model.businesstime;

/**
 * @author xuan
 * @since 2021/9/30
 */
public class HourMinute {

    private final static Integer HOUR_MIN = 0;
    private final static Integer HOUR_MAX = 23;
    private final static Integer MINUTE_MIN = 0;
    private final static Integer MINUTE_MAX = 59;

    private Integer hour;

    private Integer minute;

    public HourMinute(Integer hour, Integer minute) {
        if (null == hour || hour < HOUR_MIN || hour > HOUR_MAX) {
            throw new IllegalArgumentException(
                String.format("hour[%s] invalid. must be between [%s,%s].", hour, HOUR_MIN, HOUR_MAX));
        }

        if (null == minute || minute < MINUTE_MIN || minute > MINUTE_MAX) {
            throw new IllegalArgumentException(
                String.format("minute[%s] invalid. must be between [%s,%s].", hour, MINUTE_MIN, MINUTE_MAX));
        }

        this.hour = hour;
        this.minute = minute;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

}
