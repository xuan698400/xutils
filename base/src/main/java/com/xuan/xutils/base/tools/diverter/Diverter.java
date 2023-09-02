package com.xuan.xutils.base.tools.diverter;

/**
 * 新老链路分流器
 *
 * @author xuan
 * @since 2023/1/25
 */
public interface Diverter {

    /**
     * 指定一个Long的业务id，并对其进行hash，再根据config配置的hitNum和totalNum进行判定是否命中hitNum
     * 例如：totalNum = 100, hitNum = 10
     * 那么：
     * bizId = 103, return true.  cause：103%100=3，3<10,计算得3，>=0,so：true
     * bizId = 107, return true.  cause：107%100=7，7<10,计算得7，>=0,so：true
     * bizId = 123, return false. cause：123%100=23，23>10,计算得-1，<0,so：false
     * bizId = 1103, return true. cause：103%100=3，3<10,计算得3，>=0,so：true
     *
     * @param bizId 业务id
     * @return 如果命中hitNum，返回桶号；如果未命中返回-1；如果命中白名单返回0；如果命中黑名单返回-1
     */
    boolean isHit(long bizId);

    /**
     * 指定一个Long的业务id，并对其进行hash，再根据config配置的hitNum和totalNum进行判定落在那个分桶上
     * 例如：totalNum = 100, hitNum = 10
     * 那么：
     * bizId = 103, return 3. cause：103%100=3，3<10,返回3
     * bizId = 107, return 7. cause：107%100=7，7<10,返回7
     * bizId = 123, return -1.cause：123%100=23，23>10,返回-1
     * bizId = 1103, return 3.cause：103%100=3，3<10,返回3
     *
     * @param bizId 业务id
     * @return 如果命中hitNum，返回桶号；如果未命中返回-1；如果命中白名单返回0；如果命中黑名单返回-1
     */
    long hitNum(Long bizId);
}
