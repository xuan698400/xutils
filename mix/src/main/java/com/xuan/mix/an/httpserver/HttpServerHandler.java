package com.xuan.mix.an.httpserver;

import java.util.Map;

/**
 * @author xuan
 * @since 2022/6/23
 */
public interface HttpServerHandler {

    HttpResponse handle(String path, Map<String, String> params);
}
