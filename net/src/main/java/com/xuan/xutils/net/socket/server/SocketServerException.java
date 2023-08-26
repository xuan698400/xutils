package com.xuan.xutils.net.socket.server;

/**
 * @author xuan
 * @date 2018/1/10
 */
public class SocketServerException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SocketServerException(String message) {
        super(message);
    }

    public SocketServerException(Throwable cause) {
        super(cause);
    }

}
