package com.xuan.mix.net.miniserver;

import java.io.IOException;

/**
 * @author xuan
 * @since 2022/6/23
 */
public interface MiniServer {

    void start() throws IOException;

    void stop();

    void registerHandler(String key, MiniServerHandler httpHandler);
}
