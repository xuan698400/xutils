package com.xuan.user.common;

/**
 * @author xuan
 * @since 2020/11/17
 */
public class UserException extends RuntimeException {

    private String code;

    private String msg;

    public UserException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public UserException(UserExceptionCodeEnum userExceptionCodeEnum, Object... args) {
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
