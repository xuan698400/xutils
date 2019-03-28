package com.xuan.mix.bt.diverter;

import java.util.Set;

/**
 * 分流器配置
 *
 * @author xuan
 * @date 2019/1/29
 */
public class DiverterConfig {
    public static final int DEFAULT_PERCENT = 0;
    public static final int DEFAULT_TOTAL = 100;

    /**
     * 分流占比
     */
    private int percent = DEFAULT_PERCENT;
    /**
     * 总份数
     */
    private int total = DEFAULT_TOTAL;
    /**
     * 白名单
     */
    private Set<String> whiteSet;
    /**
     * 黑名单
     */
    private Set<String> blackSet;

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
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
