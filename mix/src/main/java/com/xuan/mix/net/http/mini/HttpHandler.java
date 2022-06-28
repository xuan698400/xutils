package com.xuan.mix.net.http.mini;

import java.util.Map;

/**
 * @author xuan
 * @since 2022/6/23
 */
public interface HttpHandler {

    HttpHandlerResponse handle(String path, Map<String, String> params);
}
