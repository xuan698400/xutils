package com.xuan.user.model.response;

/**
 * @author xuan
 * @since 2020/11/17
 */
public class UserResponse<T> {

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

    public static <E> UserResponse<E> buildError(String code, String msg) {
        UserResponse<E> response = new UserResponse<>();
        response.setSuccess(false);
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static <E> UserResponse<E> buildSuccess() {
        UserResponse<E> response = new UserResponse<>();
        response.setSuccess(true);
        return response;
    }

    public static <E> UserResponse<E> buildSuccess(E data) {
        UserResponse<E> response = new UserResponse<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

}
