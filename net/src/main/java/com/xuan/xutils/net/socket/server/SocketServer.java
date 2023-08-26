package com.xuan.xutils.net.socket.server;

/**
 * @author xuan
 * @since 2019/5/23
 */
public interface SocketServer {

    /**
     * 启动服务
     */
    void start();

    /**
     * 关闭服务
     */
    void close();

    /**
     * 发送消息
     *
     * @param message str
     */
    void sendMessage(String message);
}
