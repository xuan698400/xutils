package com.xuan.xutils.common.model.result;

import java.io.Serializable;

/**
 * 通用返回结果，各个系统无论是提供RPC接口或者HTTP接口，返回的通用Result最好统一
 *
 * @author xuan
 * @since 2022/6/1
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * true/false
     */
    private boolean success;

    /**
     * 值
     */
    private T value;

    /**
     * 结果码
     */
    private String code;

    /**
     * 结果提示
     */
    private String msg;

    /**
     * 跟踪链路的traceId
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

    /**
     * 构建成功result
     *
     * @param value 结果对象
     * @param <T>   指定泛型
     * @return result
     */
    public static <T> Result<T> success(T value) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setValue(value);
        return result;
    }

    /**
     * 构建失败result
     *
     * @param msg 错误提示
     * @param <T> 指定泛型
     * @return result
     */
    public static <T> Result<T> fail(String msg) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setMsg(msg);
        return result;
    }

    /**
     * 构建失败result
     *
     * @param code 错误码
     * @param msg  错误提示
     * @param <T>  指定泛型
     * @return result
     */
    public static <T> Result<T> fail(String code, String msg) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
