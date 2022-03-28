package com.xuan.mix.net.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.xuan.mix.net.socket.Handler;
import com.xuan.mix.net.socket.Server;
import com.xuan.mix.net.socket.SocketThreadFactory;

/**
 * @author xuan
 * @since 2019/5/23
 */
public class NioServer implements Server {

    private final Executor BOSS = new ThreadPoolExecutor(1, 1,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>(), new SocketThreadFactory("BOSS_THREAD"));

    private final Executor WORKER = new ThreadPoolExecutor(30, 30,
        1L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>(), new SocketThreadFactory("WORKER_THREAD"));

    private ServerSocketChannel serverChannel;
    private Selector selector;
    private List<SocketChannel> clientChannels;

    private volatile boolean stop = false;

    private Handler handler;

    public NioServer(int port) {
        try {
            serverChannel = ServerSocketChannel.open();
            serverChannel.socket().bind(new InetSocketAddress(port));

            selector = Selector.open();

            serverChannel.configureBlocking(false);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            clientChannels = new ArrayList<>();

            System.out.println("NioServer start...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        BOSS.execute(() -> {
            while (!stop) {
                int num = 0;
                try {
                    num = selector.select();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (num > 0) {
                    Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove();
                        if (key.isAcceptable()) {
                            clientChannels.add(accept(selector, serverChannel));
                        } else if (key.isReadable()) {
                            read(key);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void close() {
        stop = true;
        try {
            if (null != serverChannel) {
                serverChannel.close();
            }
            if (null != selector) {
                selector.close();
            }
        } catch (IOException e) {
            //Ignore
        }
    }

    @Override
    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void sendMessage(String message) {

    }

    private void handleMessage(String message) {
        WORKER.execute(() -> handler.accept(message));
    }

    private void handleFailed(Throwable e) {
        WORKER.execute(() -> handler.failed(e));
    }

    private SocketChannel accept(Selector selector, ServerSocketChannel serverChannel) {
        SocketChannel channel = null;
        try {
            channel = serverChannel.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
        } catch (Exception e) {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return channel;
    }

    private void read(SelectionKey selectionkey) {
        SocketChannel socketChannel = (SocketChannel)selectionkey.channel();
        ByteBuffer buffer = ByteBuffer.allocateDirect(6 * 1024);
        StringBuilder content = new StringBuilder();
        try {
            while (socketChannel.read(buffer) > 0) {
                content.append(byteBufferToString(buffer));
            }
            handleMessage(content.toString());
        } catch (IOException e) {
            handleFailed(e);
        }
    }

    private static String byteBufferToString(ByteBuffer buffer) {
        CharBuffer charBuffer;
        try {
            Charset charset = Charset.forName("UTF-8");
            CharsetDecoder decoder = charset.newDecoder();
            charBuffer = decoder.decode(buffer);
            buffer.flip();
            return charBuffer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

}
