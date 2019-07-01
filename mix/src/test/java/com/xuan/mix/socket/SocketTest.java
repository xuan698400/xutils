package com.xuan.mix.socket;

import com.xuan.mix.socket.aio.AioClient;
import com.xuan.mix.socket.aio.AioServer;
import com.xuan.mix.socket.nio.NioServer;
import org.junit.Test;

/**
 * @author xuan
 * @since 2019/5/20
 */
public class SocketTest {

    @Test
    public void test() {
        new Thread(() -> {
            //server
            Server server = new NioServer(20000);
            server.setHandler(new Handler() {
                @Override
                public void accept(String message) {
                    System.out.println("服务端收到消息：" + message);
                }

                @Override
                public void failed(Throwable exc) {
                    System.out.println("服务端异常：" + exc.getMessage());
                }
            });
            server.start();
        }).start();

        //new Thread(() -> {
        //    //client
        //    AioClient client = new AioClient();
        //    client.setHandler(new Handler() {
        //        @Override
        //        public void accept(String message) {
        //            System.out.println("客户端收到消息：" + message);
        //        }
        //
        //        @Override
        //        public void failed(Throwable exc) {
        //            System.out.println("客户端异常：" + exc.getMessage());
        //        }
        //    });
        //    client.connect("30.5.60.173", 20000);
        //
        //    for (int i = 0; i < 20000; i++) {
        //        try {
        //            Thread.sleep(1000);
        //        } catch (Exception e) {
        //            //Ignore
        //        }
        //        client.sendMessage("我是消息[" + i + "]");
        //    }
        //}).start();
        //
        //try {
        //    System.in.read();
        //} catch (Exception e) {
        //    //Ignore
        //}
        //System.out.println("程序退出");
    }

}
