package com.xuan.mix.common.exception;

/**
 * @author xuan
 * @since 2021/7/12
 */
public class BizException extends BaseException {

    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_ERR_CODE = "BIZ_ERROR";

    public BizException(String errMessage) {
        super(DEFAULT_ERR_CODE, errMessage);
    }

    public BizException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public BizException(BizExceptionCode resultCode, Object... msg) {
        super(resultCode.getCode(), resultCode.getMsg(msg));
    }

    public BizException(String errMessage, Throwable e) {
        super(DEFAULT_ERR_CODE, errMessage, e);
    }

    public BizException(String errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }
}
