package com.xuan.xutils.net.http.server;

/**
 * html返回处理器
 *
 * @author xuan
 * @since 2022/6/28
 */
public abstract class BaseHtmlHttpServerHandler implements HttpServerHandler {

    @Override
    public HttpResponse handle(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        response.setContentType("text/html;charset=utf-8");
        response.setContent(doHandle(request));
        return response;
    }

    protected abstract String doHandle(HttpRequest request);

}
