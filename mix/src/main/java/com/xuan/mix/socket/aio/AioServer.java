package com.xuan.mix.socket.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import com.xuan.mix.socket.Handler;

/**
 * @author xuan
 * @since 2019/5/20
 */
public class AioServer {
    private final static int BUFFER_SIZE = 1024;

    private AsynchronousServerSocketChannel channel;
    private Handler handler;

    public void start(int port) {
        try {
            channel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress("0.0.0.0", port));
            channel.accept(this, new AcceptHandler(handler));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        if (null != channel) {
            try {
                channel.close();
            } catch (IOException e) {
                //Ignore
            }
        }
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void sendMessage(String message) {
    }

    private static class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AioServer> {
        private Handler handler;

        public AcceptHandler(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void completed(AsynchronousSocketChannel result, AioServer attachment) {
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            result.read(buffer, buffer, new ReadHandler(handler));
        }

        @Override
        public void failed(Throwable exc, AioServer attachment) {
            handler.failed(exc);
        }
    }

    private static class ReadHandler implements CompletionHandler<Integer, ByteBuffer> {
        private Handler handler;

        public ReadHandler(Handler handler) {
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

}
