package com.xuan.spring.utils.nodelog.model;

/**
 * @author xuan
 * @since 2019/10/31
 */
public class NodeLogResult {

    private boolean success;

    private String resultCode;

    private String resultMsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
