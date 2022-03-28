package com.xuan.mix.net.socket;

import java.util.concurrent.ThreadFactory;

/**
 * @author xuan
 * @since 2019/5/23
 */
public class SocketThreadFactory implements ThreadFactory {
    private String name;

    public SocketThreadFactory(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread();
        thread.setName(name);
        return thread;
    }

}
