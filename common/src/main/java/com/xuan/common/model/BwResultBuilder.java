package com.xuan.common.model;

import com.xuan.common.enums.BwResultCodeEnum;

/**
 * @author xuan
 * @since 2020/3/14
 */
public class BwResultBuilder {

    public static  <T> BwResult<T> buildSuccess(T data) {
        BwResult<T> result = new BwResult<>();
        result.setCode(BwResultCodeEnum.SUCCESS.getCode());
        result.setData(data);
        return result;
    }
}
