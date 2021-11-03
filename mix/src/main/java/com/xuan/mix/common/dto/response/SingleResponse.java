package com.xuan.mix.common.dto.response;

/**
 * @author xuan
 * @since 2021/7/12
 */
public class SingleResponse<T> extends Response {
    private static final long serialVersionUID = 1L;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static SingleResponse buildSuccess() {
        SingleResponse response = new SingleResponse();
        response.setSuccess(true);
        return response;
    }

    public static <T> SingleResponse<T> buildSuccess(T data) {
        SingleResponse<T> response = new SingleResponse<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static SingleResponse buildFailure(String code, String message) {
        SingleResponse response = new SingleResponse();
        response.setSuccess(false);
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

}
