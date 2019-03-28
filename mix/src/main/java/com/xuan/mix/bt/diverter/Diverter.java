package com.xuan.mix.bt.diverter;

import java.util.Collection;
import java.util.Set;

/**
 * 分流器
 *
 * @author xuan
 * @date 2019/1/29
 */
public class Diverter {
    private final static int MISS_HIT = -1;
    private final static int WHITE_HIT = 0;

    private DiverterConfig config;

    public Diverter(DiverterConfig config) {
        this.config = config;
    }

    /**
     * 指定一个long的业务id，并对其进行hash，再根据config配置的percent和total进行判定是否命中分桶
     * 例如：total = 100, percent = 10
     * 那么：
     * bizId = 103, return true.
     * bizId = 107, return true.
     * bizId = 123, return false.
     * bizId = 1103, return true.
     *
     * @param bizId 业务id
     * @return true/false
     */
    public boolean isHit(long bizId) {
        return hitNum(bizId) >= 0;
    }

    /**
     * 指定一个long的业务id，并对其进行hash，再根据config配置的percent和total进行判定落在那个分桶上
     * 例如：total = 100, percent = 10
     * 那么：
     * bizId = 103, return 3.
     * bizId = 107, return 7.
     * bizId = 123, return -1.
     * bizId = 1103, return 3.
     *
     * @param bizId 业务id
     * @return 分桶号
     */
    public long hitNum(long bizId) {

        //1. 优先检查黑名单，在黑名单中一律不命中
        Set<String> blackSet = config.getBlackSet();
        if (isNotEmpty(blackSet)) {
            if (blackSet.contains(String.valueOf(bizId))) {
                return MISS_HIT;
            }
        }

        //2. 检查白名单，在白名单中，一律命中
        Set<String> whiteSet = config.getWhiteSet();
        if (isNotEmpty(whiteSet)) {
            if (whiteSet.contains(String.valueOf(bizId))) {
                return WHITE_HIT;
            }
        }

        //3. 校验percent和tatal的合法性，不合法的一律不命中
        int percent = config.getPercent();
        int total = config.getTotal();
        if (percent <= 0 || total <= 0) {
            return MISS_HIT;
        }

        //4. 开始计算hash，来判定是否命中
        long bucket = bizId % config.getTotal();
        if (bucket < 0) {
            bucket = Math.abs(bucket);
        }
        if (bucket < percent) {
            //命中
            return bucket;
        }

        return MISS_HIT;
    }

    private boolean isNotEmpty(Collection<String> collection) {
        return null != collection && !collection.isEmpty();
    }

}
