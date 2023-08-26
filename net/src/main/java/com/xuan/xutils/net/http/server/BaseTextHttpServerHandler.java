package com.xuan.xutils.net.http.server;

/**
 * text返回处理器
 *
 * @author xuan
 * @since 2022/6/28
 */
public abstract class BaseTextHttpServerHandler implements HttpServerHandler {

    @Override
    public HttpResponse handle(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        response.setContentType("text/plain;charset=utf-8");
        response.setContent(doHandle(request));
        return response;
    }

    protected abstract String doHandle(HttpRequest request);
}
