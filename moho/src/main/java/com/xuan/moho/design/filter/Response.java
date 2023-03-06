package com.xuan.moho.design.filter;

/**
 * @author xuan
 * @since 2023/3/6
 */
public class Response {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Response of(String message) {
        Response response = new Response();
        response.setMessage(message);
        return response;
    }

}
