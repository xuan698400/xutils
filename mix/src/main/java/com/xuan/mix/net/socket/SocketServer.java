package com.xuan.mix.net.socket;

/**
 * @author xuan
 * @since 2019/5/23
 */
public interface SocketServer {

    void start();

    void close();

    void setHandler(SocketHandler handler);

    void sendMessage(String message);
}
