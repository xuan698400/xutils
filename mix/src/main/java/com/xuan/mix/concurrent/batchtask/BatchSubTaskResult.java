package com.xuan.mix.concurrent.batchtask;

import java.util.List;

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

    private List<R> list;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
        this.setResultMsg("success");
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public List<R> getList() {
        return list;
    }

    public void setList(List<R> list) {
        this.list = list;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public static <R> BatchSubTaskResult<R> of() {
        BatchSubTaskResult<R> result = new BatchSubTaskResult<>();
        result.setSuccess(false);
        result.setResultMsg("Result init.");
        return result;
    }

}
