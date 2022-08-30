package com.xuan.mix.net.http.mini;

import java.io.IOException;

/**
 * @author xuan
 * @since 2022/6/23
 */
public interface HttpServer {

    void start() throws IOException;

    void stop();

    void registerHandler(String key, HttpHandler httpHandler);
}
