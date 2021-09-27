package com.xuan.mix.common.invoker;

/**
 * @author xuan
 * @since 2021/9/28
 */
public class BizCommondResultBuilder {

    public static <E> BizCommondResult<E> buildFail(String eCode, String eMsg) {
        BizCommondResult<E> result = new BizCommondResult<>();
        result.setSuccess(false);
        result.setCode(eCode);
        result.setMsg(eMsg);
        return result;
    }
}
