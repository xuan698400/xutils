package com.xuan.mix.common.exception;

/**
 * 所有异常的基类
 *
 * @author xuan
 * @since 2021/7/12
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(String message, Throwable e) {
        super(message, e);
    }

    public BaseException(String code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String errCode) {
        this.code = errCode;
    }

}
