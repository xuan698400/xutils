package com.xuan.moho.net.socket.client.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import com.xuan.moho.net.socket.client.SocketClient;
import com.xuan.moho.net.socket.client.SocketClientHandler;

/**
 * @author xuan
 * @since 2019/4/23
 */
public class AioSocketClient implements SocketClient {
    private final static int BUFFER_SIZE = 1024;

    private AsynchronousSocketChannel channel;
    private SocketClientHandler handler;

    private String ip;
    private int port;

    @Override
    public void connect() {
        try {
            channel = AsynchronousSocketChannel.open();
            channel.connect(new InetSocketAddress(ip, port), this, new CompletionHandler<Void, AioSocketClient>() {
                @Override
                public void completed(Void result, AioSocketClient attachment) {
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
                public void failed(Throwable exc, AioSocketClient attachment) {
                    handler.failed(exc);
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() {
        if (null != handler) {
            try {
                channel.close();
            } catch (Exception e) {
                //Ignore
            }
        }
    }

    @Override
    public void sendMessage(String message) {
        channel.write(ByteBuffer.wrap(message.getBytes()));
    }

    public static class Builder {
        private SocketClientHandler handler;
        private String ip;
        private int port;

        public Builder handler(SocketClientHandler handler) {
            this.handler = handler;
            return this;
        }

        public Builder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public AioSocketClient build() {
            checkParams();
            AioSocketClient client = new AioSocketClient();
            client.handler = this.handler;
            client.ip = this.ip;
            client.port = this.port;
            return client;
        }

        private void checkParams() {
            if (null == ip || ip.trim().length() <= 0) {
                throw new IllegalArgumentException("ip[" + ip + "] invalid");
            }
            if (port <= 0) {
                throw new IllegalArgumentException("port[" + port + "] invalid");
            }
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
