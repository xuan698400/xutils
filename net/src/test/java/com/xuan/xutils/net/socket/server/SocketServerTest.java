package com.xuan.xutils.net.socket.server;

import com.xuan.xutils.net.socket.client.SocketClient;
import com.xuan.xutils.net.socket.client.SocketClientHandler;
import com.xuan.xutils.net.socket.client.aio.AioSocketClient;
import com.xuan.xutils.net.socket.server.aio.AioSocketServer;
import org.junit.Before;
import org.junit.Test;

/**
 * @author xuan
 * @since 2019/5/20
 */
public class SocketServerTest {

    private SocketServer socketServer;

    private SocketClient socketClient;

    @Before
    public void init() {
        socketServer = AioSocketServer.builder().handler(new SocketServerHandler() {
            @Override
            public void accept(String message) {
                System.out.println("服务端收到消息：" + message);
            }

            @Override
            public void failed(Throwable exc) {
                System.out.println("服务端异常：" + exc.getMessage());
            }
        }).port(20000).build();
        socketServer.start();
        System.out.println("server init success");

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            //Ignore
        }

        socketClient = AioSocketClient.builder().handler(new SocketClientHandler() {
            @Override
            public void accept(String message) {

            }

            @Override
            public void failed(Throwable exc) {

            }
        }).ip("localhost").port(20000).build();
        socketClient.connect();
        System.out.println("client connect success");

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            //Ignore
        }
    }

    @Test
    public void test() {

        new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    //Ignore
                }
                socketClient.sendMessage("我是消息[" + i + "]");
            }
        }).start();

        try {
            System.in.read();
        } catch (Exception e) {
            //Ignore
        }
        System.out.println("程序退出");
    }

}
