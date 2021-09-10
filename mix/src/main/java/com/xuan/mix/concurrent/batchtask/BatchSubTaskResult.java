package com.xuan.mix.concurrent.batchtask;

/**
 * 分批子任务的处理结果
 *
 * @author xuan
 * @since 2021/9/10
 */
public class BatchSubTaskResult<R> {

    private boolean success;

    private String resultMsg;

    private Exception exception;

    private R data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public R getData() {
        return data;
    }

    public void setData(R data) {
        this.data = data;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

}
