package com.xuan.moho.net.socket.server;

/**
 * @author xuan
 * @since 2019/5/20
 */
public interface SocketServerHandler {

    /**
     * 接受消息
     *
     * @param message 字符串消息
     */
    void accept(String message);

    /**
     * 失败回调
     *
     * @param exc 异常
     */
    void failed(Throwable exc);
}
