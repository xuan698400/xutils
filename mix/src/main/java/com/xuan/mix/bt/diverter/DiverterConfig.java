package com.xuan.mix.bt.diverter;

import java.util.Set;

/**
 * 分流器配置
 * Created by xuan on 2019/1/29.
 */
public class DiverterConfig {

    /**
     * 分流占比
     */
    private Integer percent;
    /**
     * 总份数
     */
    private Integer total;
    /**
     * 白名单
     */
    private Set<String> whiteSet;
    /**
     * 黑名单
     */
    private Set<String> blackSet;

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Set<String> getWhiteSet() {
        return whiteSet;
    }

    public void setWhiteSet(Set<String> whiteSet) {
        this.whiteSet = whiteSet;
    }

    public Set<String> getBlackSet() {
        return blackSet;
    }

    public void setBlackSet(Set<String> blackSet) {
        this.blackSet = blackSet;
    }

}
