package com.xuan.crudboy.api;

import java.util.Map;

/**
 * @author xuan
 * @since 2023/11/5
 */
public interface CbApi {

    /**
     * 新增
     *
     * @param data 新增数据
     * @return result
     */
    CbResult<Long> add(Map<String, Object> data);
}
