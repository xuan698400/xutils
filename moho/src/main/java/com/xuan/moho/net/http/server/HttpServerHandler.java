package com.xuan.moho.net.http.server;

/**
 * HTTP处理器
 *
 * @author xuan
 * @since 2022/6/23
 */
public interface HttpServerHandler {

    HttpResponse handle(HttpRequest request);
}
