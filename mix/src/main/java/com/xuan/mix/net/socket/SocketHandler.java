package com.xuan.mix.net.socket;

/**
 * @author xuan
 * @since 2019/5/20
 */
public interface SocketHandler {

    void accept(String message);

    void failed(Throwable exc);
}
