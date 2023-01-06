package com.xuan.mix.an.common.exception;

/**
 * @author xuan
 * @since 2021/7/12
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String code;

    public BizException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public BizException(String code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
    }

    public BizException(BizExceptionCode code, Throwable e) {
        super(code.getMsg(), e);
        this.code = code.getCode();
    }

    public String getCode() {
        return code;
    }

}
