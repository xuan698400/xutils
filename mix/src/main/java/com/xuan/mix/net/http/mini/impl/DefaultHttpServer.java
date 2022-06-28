package com.xuan.mix.net.http.mini.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.spi.HttpServerProvider;
import com.xuan.mix.net.http.mini.HttpHandler;
import com.xuan.mix.net.http.mini.HttpHandlerResponse;
import com.xuan.mix.net.http.mini.HttpServer;

/**
 * @author xuan
 * @since 2022/6/23
 */
public class DefaultHttpServer implements HttpServer {

    private AtomicBoolean isStart = new AtomicBoolean(false);

    private int port;

    private AtomicInteger threadCounter = new AtomicInteger(1);

    private Executor executor = new ThreadPoolExecutor(4, 4, 4,
        TimeUnit.MINUTES, new LinkedBlockingQueue<>(1000),
        (r) -> new Thread(r, "xUtils-DefaultHttpServer-Thread" + threadCounter.get()), (r, executor) -> {
    });

    private Map<String, HttpHandler> handlerMap = new HashMap<>();

    private com.sun.net.httpserver.HttpServer sunHttpServer;

    public DefaultHttpServer(int port) {
        this.port = port;
    }

    @Override
    public void start() throws IOException {
        if (!isStart.compareAndSet(false, true)) {
            return;
        }

        HttpServerProvider httpServerProvider = HttpServerProvider.provider();
        sunHttpServer = httpServerProvider.createHttpServer(new InetSocketAddress(this.port), 10);

        sunHttpServer.createContext("/", (httpExchange) -> {

            HttpHandler httpHandler = handlerMap.get(httpExchange.getRequestURI().getPath().split("\\.")[1]);
            HttpHandlerResponse response;
            if (null != httpHandler) {
                response = httpHandler.handle(httpExchange.getRequestURI().getPath(),
                    buildGetParams(httpExchange.getRequestURI()));
            } else {
                HttpHandlerResponse error = new HttpHandlerResponse();
                error.setContentType("text/plain;charset=utf-8");
                error.setContent("NO_HTTP_HANDLER_FOUND");
                response = error;
            }

            Headers headers = httpExchange.getResponseHeaders();
            headers.set("Content-Type", response.getContentType());

            byte[] bytes = response.getContent().getBytes(Charset.forName("UTF-8"));
            httpExchange.sendResponseHeaders(200, bytes.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(bytes);
            os.close();
        });

        sunHttpServer.setExecutor(this.executor);

        sunHttpServer.start();
        System.out.println("xUtils_DefaultHttpServer_started. port=" + port);
    }

    @Override
    public void stop() {
        if (!isStart.compareAndSet(true, false)) {
            return;
        }

        sunHttpServer.stop(1);
        System.out.println("xUtils_DefaultHttpServer_stop.");
    }

    @Override
    public void registerHandler(String key, HttpHandler httpHandler) {
        handlerMap.put(key, httpHandler);
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

}
