package com.xuan.common.model;

/**
 * @author xuan
 * @since 2019/11/18
 */
public class BwResult<T> {

    private boolean success;

    private long serverTime = System.currentTimeMillis();

    private String code;

    private String msg;

    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <E> BwResult<E> obtainError(String eCode, String eMsg) {
        BwResult<E> bwResult = new BwResult<>();
        bwResult.setSuccess(false);
        bwResult.setCode(eCode);
        bwResult.setMsg(eMsg);
        return bwResult;
    }

    public static <E> BwResult<E> obtainSuccess() {
        BwResult<E> bwResult = new BwResult<>();
        bwResult.setSuccess(true);
        return bwResult;
    }

    public static <E> BwResult<E> obtainSuccess(E data) {
        BwResult<E> bwResult = new BwResult<>();
        bwResult.setSuccess(true);
        bwResult.setData(data);
        return bwResult;
    }

}
