package com.xuan.spring.utils.nodelog.testservice;

/**
 * @author xuan
 * @since 2021/9/4
 */
public class TestResult {

    private boolean success;

    private String code;

    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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
}
