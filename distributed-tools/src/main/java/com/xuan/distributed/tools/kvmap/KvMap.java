package com.xuan.distributed.tools.kvmap;

/**
 * @author xuan
 * @since 2022/9/10
 */
public interface KvMap {

    KvMapResult put(String namespace, String key, String value, long version, long expireTimeSecond);

    KvMapResult put(String namespace, String key, String value, long expireTimeSecond);

    KvMapResult get(String namespace, String key);

    KvMapResult remove(String namespace, String key);

}
