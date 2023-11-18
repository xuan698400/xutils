package com.xuan.crudboy.api.core.add;

import com.xuan.crudboy.api.CbResult;

/**
 * @author xuan
 * @since 2023/11/10
 */
public interface CbAddApi {

    CbResult<Long> add(CbAddRequest request);
}
