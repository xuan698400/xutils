package com.xuan.xutils.net.socket.server.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import com.xuan.xutils.net.socket.server.SocketServer;
import com.xuan.xutils.net.socket.server.SocketServerException;
import com.xuan.xutils.net.socket.server.SocketServerHandler;

/**
 * @author xuan
 * @since 2019/5/20
 */
public class AioSocketServer implements SocketServer {
    private final static int BUFFER_SIZE = 1024;

    private AsynchronousServerSocketChannel channel;

    private SocketServerHandler handler;
    private int port;

    @Override
    public void start() {
        try {
            channel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress("0.0.0.0", port));
            channel.accept(this, new AcceptHandler(handler));
        } catch (IOException e) {
            throw new SocketServerException(e);
        }
    }

    @Override
    public void close() {
        if (null != channel) {
            try {
                channel.close();
            } catch (IOException e) {
                //Ignore
            }
        }
    }

    @Override
    public void sendMessage(String message) {
    }

    private static class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AioSocketServer> {
        private SocketServerHandler handler;

        public AcceptHandler(SocketServerHandler handler) {
            this.handler = handler;
        }

        @Override
        public void completed(AsynchronousSocketChannel result, AioSocketServer attachment) {
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            result.read(buffer, buffer, new ReadHandler(handler));
        }

        @Override
        public void failed(Throwable exc, AioSocketServer attachment) {
            handler.failed(exc);
        }
    }

    private static class ReadHandler implements CompletionHandler<Integer, ByteBuffer> {
        private SocketServerHandler handler;

        public ReadHandler(SocketServerHandler handler) {
            this.handler = handler;
        }

        @Override
        public void completed(Integer result, ByteBuffer attachment) {
            attachment.flip();
            CharBuffer charBuffer = CharBuffer.allocate(BUFFER_SIZE);
            CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
            decoder.decode(attachment, charBuffer, false);
            charBuffer.flip();
            String data = new String(charBuffer.array(), 0, charBuffer.limit());
            if (null != handler) {
                handler.accept(data);
            }
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            handler.failed(exc);
        }
    }

    public static class Builder {
        private SocketServerHandler handler;
        private int port;

        public Builder handler(SocketServerHandler handler) {
            this.handler = handler;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public AioSocketServer build() {
            checkParams();
            AioSocketServer server = new AioSocketServer();
            server.handler = this.handler;
            server.port = this.port;
            return server;
        }

        private void checkParams() {
            if (port <= 0) {
                throw new IllegalArgumentException("port[" + port + "] invalid");
            }
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
