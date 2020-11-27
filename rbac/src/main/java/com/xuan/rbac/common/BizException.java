package com.xuan.rbac.common;

/**
 * @author xuan
 * @since 2020/11/17
 */
public class BizException extends RuntimeException {

    private String code;

    private String msg;

    public BizException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BizException(ErrorCodeEnum userExceptionCodeEnum, Object... args) {
        this.code = userExceptionCodeEnum.getCode();
        this.msg = userExceptionCodeEnum.getMsg(args);
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
