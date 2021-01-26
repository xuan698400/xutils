package com.xuan.common.exception;

/**
 * @author xuan
 * @since 2019/11/16
 */
public class BizException extends RuntimeException {

    private String code;

    private String msg;

    public BizException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BizException(BizExceptionCode resultCode, Object... msg) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg(msg);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
