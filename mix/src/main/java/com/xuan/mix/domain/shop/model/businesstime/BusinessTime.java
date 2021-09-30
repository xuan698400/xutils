package com.xuan.mix.domain.shop.model.businesstime;

/**
 * @author xuan
 * @since 2021/9/30
 */
public class BusinessTime {

    private NormalBusinessTime normalBusinessTime;

    private SpecialBusinessTime specialBusinessTime;

    public NormalBusinessTime getNormalBusinessTime() {
        return normalBusinessTime;
    }

    public void setNormalBusinessTime(NormalBusinessTime normalBusinessTime) {
        this.normalBusinessTime = normalBusinessTime;
    }

    public SpecialBusinessTime getSpecialBusinessTime() {
        return specialBusinessTime;
    }

    public void setSpecialBusinessTime(SpecialBusinessTime specialBusinessTime) {
        this.specialBusinessTime = specialBusinessTime;
    }

}
