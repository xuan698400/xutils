package com.xuan.mix.socket;

/**
 * @author xuan
 * @since 2019/5/23
 */
public interface Server {

    void start();

    void close();

    void setHandler(Handler handler);

    void sendMessage(String message);
}
