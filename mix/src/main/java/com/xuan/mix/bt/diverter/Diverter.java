package com.xuan.mix.bt.diverter;

import java.util.Collection;
import java.util.Set;

/**
 * 新老链路分流器。
 *
 * @author xuan
 * @date 2019/1/29
 */
public class Diverter {
    private final static int MISS_HIT = -1;
    private final static int WHITE_HIT = 0;

    /**
     * 指定一个Long的业务id，并对其进行hash，再根据config配置的hitNum和totalNum进行判定是否命中hitNum
     * 例如：totalNum = 100, hitNum = 10
     * 那么：
     * bizId = 103, return true.  cause：103%100=3，3<10,计算得3，>=0,so：true
     * bizId = 107, return true.  cause：107%100=7，7<10,计算得7，>=0,so：true
     * bizId = 123, return false. cause：123%100=23，23>10,计算得-1，<0,so：false
     * bizId = 1103, return true. cause：103%100=3，3<10,计算得3，>=0,so：true
     *
     * @param bizId  业务id
     * @param config 配置
     * @return 如果命中hitNum，返回桶号；如果未命中返回-1；如果命中白名单返回0；如果命中黑名单返回-1
     */
    public static boolean isHit(long bizId, DiverterConfig config) {
        return hitNum(bizId, config) >= 0;
    }

    /**
     * 指定一个Long的业务id，并对其进行hash，再根据config配置的hitNum和totalNum进行判定落在那个分桶上
     * 例如：totalNum = 100, hitNum = 10
     * 那么：
     * bizId = 103, return 3. cause：103%100=3，3<10,返回3
     * bizId = 107, return 7. cause：107%100=7，7<10,返回7
     * bizId = 123, return -1.cause：123%100=23，23>10,返回-1
     * bizId = 1103, return 3.cause：103%100=3，3<10,返回3
     *
     * @param bizId  业务id
     * @param config 配置
     * @return 如果命中hitNum，返回桶号；如果未命中返回-1；如果命中白名单返回0；如果命中黑名单返回-1
     */
    public static long hitNum(Long bizId, DiverterConfig config) {
        //如果分流参数是NULL，默认不命中新链路
        if (null == bizId) {
            return MISS_HIT;
        }

        // 校验配置的合法性，如果配置不合法，默认不命中新链路
        if (null == config || invalidNum(config.getHitNum()) || invalidNum(config.getTotalNum())) {
            return MISS_HIT;
        }

        //优先检查黑名单，在黑名单中的一律不命中新链路
        Set<String> blackSet = config.getBlackSet();
        if (isNotEmpty(blackSet) && blackSet.contains(String.valueOf(bizId))) {
            return MISS_HIT;
        }

        //再检查白名单，在白名单中，一律命中新链路
        Set<String> whiteSet = config.getWhiteSet();
        if (isNotEmpty(whiteSet) && whiteSet.contains(String.valueOf(bizId))) {
            return WHITE_HIT;
        }

        //开始计算hash，来判定是否命中
        long bucket = bizId % config.getTotalNum();
        if (bucket < 0) {
            //考虑到bizId业务是负数，这里做一次纠正
            bucket = Math.abs(bucket);
        }
        if (bucket < config.getHitNum()) {
            //命中hitNum中的桶，返回桶号
            return bucket;
        }
        //未命中
        return MISS_HIT;
    }

    private static boolean isNotEmpty(Collection<String> collection) {
        return null != collection && !collection.isEmpty();
    }

    private static boolean invalidNum(Integer num) {
        return null == num || num < 0;
    }

}
