package com.xuan.mix.bt.mapping;

import java.util.Map;

/**
 * @author xuan
 * @since 2021/9/14
 */
public interface Mapping {

    /**
     * 从from对象映射一些数据到Map
     *
     * @param from   来源对象
     * @param config 映射配置
     * @return 映射后数据Map
     */
    Map<String, Object> mapping(Object from, MappingConfig config);
}
