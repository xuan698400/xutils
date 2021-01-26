package com.xuan.common.model;

import com.xuan.common.exception.BizExceptionCodeEnum;

/**
 * @author xuan
 * @since 2020/3/14
 */
public class BizResultBuilder {

    public static <T> BizResult<T> buildSuccess(T data) {
        BizResult<T> result = new BizResult<>();
        result.setSuccess(true);
        result.setCode(BizExceptionCodeEnum.SUCCESS.getCode());
        result.setData(data);
        return result;
    }

    public static <E> BizResult<E> buildSuccess() {
        BizResult<E> result = new BizResult<>();
        result.setSuccess(true);
        result.setCode(BizExceptionCodeEnum.SUCCESS.getCode());
        return result;
    }

    public static <E> BizResult<E> buildFail(String eCode, String eMsg) {
        BizResult<E> bwResult = new BizResult<>();
        bwResult.setSuccess(false);
        bwResult.setCode(eCode);
        bwResult.setMsg(eMsg);
        return bwResult;
    }

}
