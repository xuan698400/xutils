package com.xuan.xutils.net.socket.server.core;

import java.util.concurrent.ThreadFactory;

/**
 * @author xuan
 * @since 2019/5/23
 */
public class SocketServerThreadFactory implements ThreadFactory {
    private String name;

    public SocketServerThreadFactory(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread();
        thread.setName(name);
        return thread;
    }

}
