package com.xuan.mix.common.exception;

/**
 * @author xuan
 * @since 2021/7/12
 */
public class ExceptionFactory {

    public static BizException bizException(String message) {
        return new BizException(message);
    }

    public static BizException bizException(String code, String message) {
        return new BizException(code, message);
    }

    public static BizException bizException(BizExceptionCode code, Object... params) {
        return new BizException(code, params);
    }

    public static SysException sysException(String message) {
        return new SysException(message);
    }

    public static SysException sysException(String code, String message) {
        return new SysException(code, message);
    }

    public static SysException sysException(String message, Throwable e) {
        return new SysException(message, e);
    }

    public static SysException sysException(String code, String message, Throwable e) {
        return new SysException(code, message, e);
    }

}
