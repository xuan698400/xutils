package com.xuan.mix.common.invoker;

import com.xuan.mix.common.exception.BizException;

/**
 * 使用该call方法包装，可以进行手动AOP拦截（改装版策略模式）
 *
 * @author xuan
 * @since 2021/1/19
 */
public class BizInvoker {

    public static <T> BizCommondResult<T> call(BizCommond<T> commond) {
        try {
            return commond.call();
        } catch (BizException be) {
            return buildFail(be.getErrCode(), be.getMessage());
        } catch (Exception e) {
            return buildFail(BizException.DEFAULT_ERR_CODE, e.getMessage());
        }
    }

    private static <E> BizCommondResult<E> buildFail(String eCode, String eMsg) {
        BizCommondResult<E> result = new BizCommondResult<>();
        result.setSuccess(false);
        result.setCode(eCode);
        result.setMsg(eMsg);
        return result;
    }

}
