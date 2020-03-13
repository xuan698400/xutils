package com.xuan.common.exception;

import com.xuan.common.model.BwResultCode;

/**
 * @author xuan
 * @since 2019/11/16
 */
public class BwException extends RuntimeException {

    private String code;

    private String msg;

    public BwException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BwException(BwResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
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
