package com.xuan.mix.net.socket.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import com.xuan.mix.net.socket.Handler;

/**
 * @author xuan
 * @since 2019/4/23
 */
public class AioClient {
    private final static int BUFFER_SIZE = 1024;

    private AsynchronousSocketChannel channel;
    private Handler handler;

    public void connect(String ip, int port) {
        try {
            channel = AsynchronousSocketChannel.open();
            channel.connect(new InetSocketAddress(ip, port), this, new CompletionHandler<Void, AioClient>() {
                @Override
                public void completed(Void result, AioClient attachment) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
                    channel.read(byteBuffer, this, new CompletionHandler<Integer, Object>() {
                        @Override
                        public void completed(Integer result, Object attachment) {
                            handler.accept(new String(byteBuffer.array()));
                        }

                        @Override
                        public void failed(Throwable exc, Object attachment) {
                            handler.failed(exc);
                        }
                    });
                }

                @Override
                public void failed(Throwable exc, AioClient attachment) {
                    handler.failed(exc);
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        if (null != handler) {
            try {
                channel.close();
            } catch (Exception e) {
                //Ignore
            }
        }
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void sendMessage(String message) {
        channel.write(ByteBuffer.wrap(message.getBytes()));
    }

}
