package com.xuan.mix.common.invoker;

/**
 * @author xuan
 * @since 2021/9/28
 */
public class BizCommandResultBuilder {

    public static <T> BizCommandResult<T> buildFail(String code, String msg) {
        BizCommandResult<T> result = new BizCommandResult<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> BizCommandResult<T> buildSuccess(T data) {
        BizCommandResult<T> result = new BizCommandResult<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

}
