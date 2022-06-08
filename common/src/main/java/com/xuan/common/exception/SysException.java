package com.xuan.common.exception;

/**
 * @author xuan
 * @since 2021/7/12
 */
public class SysException extends BaseException {
    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_ERR_CODE = "SYS_ERROR";

    public SysException(String message) {
        super(DEFAULT_ERR_CODE, message);
    }

    public SysException(String code, String message) {
        super(code, message);
    }

    public SysException(String message, Throwable e) {
        super(DEFAULT_ERR_CODE, message, e);
    }

    public SysException(String code, String message, Throwable e) {
        super(code, message, e);
    }
}
