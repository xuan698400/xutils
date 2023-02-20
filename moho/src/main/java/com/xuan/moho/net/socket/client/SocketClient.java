package com.xuan.moho.net.socket.client;

/**
 * @author xuan
 * @since 2019/5/24
 */
public interface SocketClient {

    void connect();

    void close();
    
    void sendMessage(String message);
}