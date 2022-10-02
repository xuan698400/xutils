package com.xuan.mix.an.httpserver.handler;

import java.util.Map;

import com.xuan.mix.an.httpserver.HttpServerHandler;
import com.xuan.mix.an.httpserver.HttpResponse;

/**
 * @author xuan
 * @since 2022/6/28
 */
public abstract class BaseTextHttpServerHandler implements HttpServerHandler {

    @Override
    public HttpResponse handle(String path, Map<String, String> params) {
        HttpResponse response = new HttpResponse();
        response.setContentType("text/plain;charset=utf-8");
        response.setContent(doHandle(path, params));
        return response;
    }

    protected abstract String doHandle(String path, Map<String, String> params);
}
