package com.xuan.mix.an.common.exception;

/**
 * @author xuan
 * @since 2021/7/12
 */
public class ExceptionFactory {

    public static BizException bizException(String code, String message) {
        return new BizException(code, message);
    }

    public static BizException bizException(BizExceptionCode code, Object... params) {
        return new BizException(code.getCode(), code.getMsg(params));
    }

    public static BizException bizException(BizExceptionCode code, Throwable e, Object... params) {
        return new BizException(code.getCode(), code.getMsg(params), e);
    }

}
