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

    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public static <T> Result<T> success(T value) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setValue(value);
        return result;
    }

    public static <T> Result<T> fail(String msg) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> fail(String code, String msg) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
