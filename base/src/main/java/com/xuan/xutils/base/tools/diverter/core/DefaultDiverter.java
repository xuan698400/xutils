package com.xuan.xutils.base.tools.diverter.core;

import java.util.Set;

import com.xuan.xutils.base.tools.diverter.Diverter;

/**
 * @author xuan
 * @since 2023/1/25
 */
public class DefaultDiverter implements Diverter {
    private final static int MISS_HIT = -1;
    private final static int WHITE_HIT = 0;

    private static final Integer DEFAULT_HIT_NUM = 0;
    private static final Integer DEFAULT_TOTAL_NUM = 100;

    /**
     * 分流占比
     */
    private Integer hitNum;
    /**
     * 总份数
     */
    private Integer totalNum;
    /**
     * 白名单
     */
    private Set<Long> whiteList;
    /**
     * 黑名单
     */
    private Set<Long> blackList;

    @Override
    public boolean isHit(long bizId) {
        return hitNum(bizId) >= 0;
    }

    @Override
    public long hitNum(Long bizId) {
        //如果分流参数是NULL，默认不命中新链路
        if (null == bizId) {
            return MISS_HIT;
        }

        //优先检查黑名单，在黑名单中的一律不命中新链路
        if (null != blackList && blackList.contains(bizId)) {
            return MISS_HIT;
        }

        //再检查白名单，在白名单中，一律命中新链路
        if (null != whiteList && whiteList.contains(bizId)) {
            return WHITE_HIT;
        }

        //开始计算hash，来判定是否命中
        long bucket = bizId % totalNum;
        if (bucket < 0) {
            //考虑到bizId业务是负数，这里做一次纠正
            bucket = Math.abs(bucket);
        }
        if (bucket < hitNum) {
            //命中hitNum中的桶，返回桶号
            return bucket;
        }
        //未命中
        return MISS_HIT;
    }

    public static class Builder {
        private Integer hitNum;
        private Integer totalNum;
        private Set<Long> whiteList;
        private Set<Long> blackList;

        public Builder hitNum(Integer hitNum) {
            this.hitNum = hitNum;
            return this;
        }

        public Builder totalNum(Integer totalNum) {
            this.totalNum = totalNum;
            return this;
        }

        public Builder whiteList(Set<Long> whiteList) {
            this.whiteList = whiteList;
            return this;
        }

        public Builder blackList(Set<Long> blackList) {
            this.blackList = blackList;
            return this;
        }

        public DefaultDiverter build() {
            checkParams();
            DefaultDiverter defaultDiverter = new DefaultDiverter();
            defaultDiverter.hitNum = this.hitNum;
            defaultDiverter.totalNum = this.totalNum;
            defaultDiverter.whiteList = this.whiteList;
            defaultDiverter.blackList = this.blackList;
            return defaultDiverter;
        }

        private void checkParams() {
            if (null == hitNum || hitNum < 0) {
                throw new IllegalArgumentException(String.format("hitNum[%s] invalid.", hitNum));
            }
            if (null == totalNum || totalNum < 0) {
                throw new IllegalArgumentException(String.format("totalNum[%s] invalid.", totalNum));
            }
            if (hitNum > totalNum) {
                throw new IllegalArgumentException(
                    String.format("hitNum[%s] cannot be greater than totalNum[%s].", hitNum, totalNum));
            }
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
