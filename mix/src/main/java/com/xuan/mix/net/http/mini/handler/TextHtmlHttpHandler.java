package com.xuan.mix.net.http.mini.handler;

import java.util.Map;

import com.xuan.mix.net.http.mini.HttpHandler;
import com.xuan.mix.net.http.mini.HttpHandlerResponse;

/**
 * @author xuan
 * @since 2022/6/28
 */
public abstract class TextHtmlHttpHandler implements HttpHandler {

    @Override
    public HttpHandlerResponse handle(String path, Map<String, String> params) {
        HttpHandlerResponse response = new HttpHandlerResponse();
        response.setContentType("text/html;charset=utf-8");
        response.setContent(doHandle(path, params));
        return response;
    }

    protected abstract String doHandle(String path, Map<String, String> params);
}
