package com.xuan.mix.http.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.spi.HttpServerProvider;

/**
 * 一个超级简单的Http服务器，目前只支持GET请求
 *
 * @author xuan
 * @since 2020/10/16
 */
public class HttpServer {
    /**
     * 表示是否已经启动了
     */
    private AtomicBoolean isStart = new AtomicBoolean(false);
    /**
     * 服务器开放端口
     */
    private int port;
    /**
     * 线程池创建线程时，使用该计数器
     */
    private AtomicInteger threadCounter = new AtomicInteger(1);
    /**
     * 创建线程池
     */
    private Executor executor = new ThreadPoolExecutor(4, 4, 4,
        TimeUnit.MINUTES, new LinkedBlockingQueue<>(1000),
        (r) -> new Thread(r, "xUtils-HttpServer-Thread" + threadCounter.get()), (r, executor) -> {
        //这里是拒绝策略，默认抛弃
    });
    /**
     * 逻辑处理
     */
    private HttpHandler httpHandler;
    /**
     * 内部正真使用的Server
     */
    private com.sun.net.httpserver.HttpServer innerHttpServer;

    public HttpServer(int port, HttpHandler httpHandler) {
        this.port = port;
        this.httpHandler = httpHandler;
    }

    public void start() throws IOException {
        if (!isStart.compareAndSet(false, true)) {
            return;
        }

        HttpServerProvider httpServerProvider = HttpServerProvider.provider();
        innerHttpServer = httpServerProvider.createHttpServer(new InetSocketAddress(this.port), 10);

        innerHttpServer.createContext("/", (httpExchange) -> {
            String res = "no hander";
            if (null != httpHandler) {
                res = httpHandler.handle(httpExchange.getRequestURI().getPath(),
                    buildGetParams(httpExchange.getRequestURI()));
            }

            Headers responseHeaders = httpExchange.getResponseHeaders();
            responseHeaders.set("Content-Type", "text/plain;charset=utf-8");

            byte[] bytes = res.getBytes(Charset.forName("UTF-8"));
            httpExchange.sendResponseHeaders(200, bytes.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(bytes);
            os.close();
        });

        innerHttpServer.setExecutor(this.executor);

        innerHttpServer.start();
        System.out.println("xUtils HttpServer start. port=" + port);
    }

    public void stop() {
        if (!isStart.compareAndSet(true, false)) {
            return;
        }

        innerHttpServer.stop(1);
        System.out.println("xUtils HttpServer stop.");
    }

    private Map<String, String> buildGetParams(URI uri) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();
        String query = uri.getQuery();
        if (query != null) {
            String[] queryParams = query.split("&");
            if (queryParams.length > 0) {
                for (String kv : queryParams) {
                    String[] param = kv.split("=");
                    String k = URLDecoder.decode(param[0], "utf-8");
                    String v = URLDecoder.decode(param[1], "utf-8");
                    params.put(k, v);
                }
            }
        }
        return params;
    }

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = new HttpServer(7002, new HttpHandler() {
            @Override
            public String handle(String path, Map<String, String> params) {
                System.out.println("path:" + path + ", params:" + params);
                return "我收到path:" + path + ", params:" + params;
            }
        });

        httpServer.start();

        new Scanner(System.in).next();
        httpServer.stop();
    }

}
