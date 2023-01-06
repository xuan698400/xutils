package com.xuan.moho.base.exception;

/**
 * @author xuan
 * @since 2021/7/12
 */
public class ExceptionFactory {

    public static BizException exception(String code, String msg) {
        return new BizException(code, msg);
    }

    public static BizException exception(String code, String msg, Throwable e) {
        return new BizException(code, msg, e);
    }

    public static BizException exception(BizExceptionCode code, Object... params) {
        return new BizException(code.getCode(), code.getMsg(params));
    }

    public static BizException exception(BizExceptionCode code, Throwable e, Object... params) {
        return new BizException(code.getCode(), code.getMsg(params), e);
    }

    public static BizException bizException(String msg) {
        return ExceptionFactory.exception(BizExceptionCodeEnum.BIZ_EXCEPTION, msg);
    }

    public static BizException bizException(String msg, Throwable e) {
        return ExceptionFactory.exception(BizExceptionCodeEnum.BIZ_EXCEPTION, e, msg);
    }

    public static BizException dbException(String msg) {
        return ExceptionFactory.exception(BizExceptionCodeEnum.DB_EXCEPTION, msg);
    }

    public static BizException dbException(String msg, Throwable e) {
        return ExceptionFactory.exception(BizExceptionCodeEnum.DB_EXCEPTION, e, msg);
    }

    public static BizException rpcException(String msg) {
        return ExceptionFactory.exception(BizExceptionCodeEnum.RPC_EXCEPTION, msg);
    }

    public static BizException rpcException(String msg, Throwable e) {
        return ExceptionFactory.exception(BizExceptionCodeEnum.RPC_EXCEPTION, e, msg);
    }

    public static BizException unknownException(String msg) {
        return ExceptionFactory.exception(BizExceptionCodeEnum.UNKNOWN_EXCEPTION, msg);
    }

    public static BizException unknownException(String msg, Throwable e) {
        return ExceptionFactory.exception(BizExceptionCodeEnum.UNKNOWN_EXCEPTION, e, msg);
    }

}
