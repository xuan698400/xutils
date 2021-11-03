package com.xuan.mix.common.exception;

/**
 * @author xuan
 * @since 2021/7/12
 */
public class BizException extends BaseException {

    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_ERR_CODE = "BIZ_ERROR";

    public BizException(String message) {
        super(DEFAULT_ERR_CODE, message);
    }

    public BizException(String code, String message) {
        super(code, message);
    }

    public BizException(BizExceptionCode code, Object... params) {
        super(code.getCode(), code.getMsg(params));
    }

    public BizException(String message, Throwable e) {
        super(DEFAULT_ERR_CODE, message, e);
    }

    public BizException(String code, String message, Throwable e) {
        super(code, message, e);
    }
}
