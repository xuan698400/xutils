package com.xuan.common.model.result;

import java.io.Serializable;

/**
 * @author xuan
 * @since 2022/6/1
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 查询返回结果
     */
    private T value;
    /**
     * 结果码，如果结果是错误的，存放错误码
     */
    private String code;
    /**
     * 结果信息，如果结果是错误的，存放错误提示
     */
    private String message;
    /**
     * 链路跟踪ID
     */
    private String traceId;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

}
