package com.xuan.common.model.result;

import java.io.Serializable;

/**
 * @author xuan
 * @since 2022/6/1
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean success;

    private T value;

    private String code;

    private String message;

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
