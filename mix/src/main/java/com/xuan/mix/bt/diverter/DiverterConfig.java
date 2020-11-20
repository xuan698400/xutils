package com.xuan.mix.bt.diverter;

import java.util.Set;

/**
 * 分流器配置，一般配置到配置中心可调节
 *
 * @author xuan
 * @date 2019/1/29
 */
public class DiverterConfig {
    private static final Integer DEFAULT_HIT_NUM = 0;
    private static final Integer DEFAULT_TOTAL_NUM = 100;

    /**
     * 分流占比
     */
    private Integer hitNum = DEFAULT_HIT_NUM;
    /**
     * 总份数
     */
    private Integer totalNum = DEFAULT_TOTAL_NUM;
    /**
     * 白名单
     */
    private Set<String> whiteSet;
    /**
     * 黑名单
     */
    private Set<String> blackSet;

    public Integer getHitNum() {
        return hitNum;
    }

    public void setHitNum(Integer hitNum) {
        this.hitNum = hitNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
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
