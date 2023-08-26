package com.xuan.xutils.net.http.server;

import java.io.IOException;

/**
 * HTTP服务器
 *
 * @author xuan
 * @since 2022/6/23
 */
public interface HttpServer {

    /**
     * 启动服务器
     *
     * @throws IOException
     */
    void start() throws IOException;

    /**
     * 关闭服务器
     */
    void stop();

    /**
     * 注册处理器
     *
     * @param key         一般是访问地址后缀，例如：http://localhost:8888/xxxxxx.html，那么可以注册处理html对应的处理器
     * @param httpHandler 处理器
     */
    void registerHandler(String key, HttpServerHandler httpHandler);
}
