package com.xuan.moho.net.socket.client;

/**
 * @author xuan
 * @since 2019/5/20
 */
public interface SocketClientHandler {

    void accept(String message);

    void failed(Throwable exc);
}
