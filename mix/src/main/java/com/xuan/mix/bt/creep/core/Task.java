package com.xuan.mix.bt.creep.core;

/**
 * @author xuan
 * @since 2020/11/1
 */
public class Task<T> {

    private Request request;

    private Response response;

    private ResponseParser<T> responsePaser;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public ResponseParser<T> getResponsePaser() {
        return responsePaser;
    }

    public void setResponsePaser(ResponseParser<T> responsePaser) {
        this.responsePaser = responsePaser;
    }

}
