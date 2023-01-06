package com.xuan.moho.net.httpserver.core;

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
import com.xuan.moho.base.log.Logger;
import com.xuan.moho.base.log.LoggerFactory;
import com.xuan.moho.net.httpserver.HttpRequest;
import com.xuan.moho.net.httpserver.HttpResponse;
import com.xuan.moho.net.httpserver.HttpServer;
import com.xuan.moho.net.httpserver.HttpServerHandler;

/**
 * @author xuan
 * @since 2022/6/23
 */
public class DefaultHttpServer implements HttpServer {

    private static final Logger log = LoggerFactory.getLogger(DefaultHttpServer.class);

    private AtomicBoolean isStart = new AtomicBoolean(false);

    private int port;

    private AtomicInteger threadCounter = new AtomicInteger(1);

    private Executor executor = new ThreadPoolExecutor(4, 4, 4,
        TimeUnit.MINUTES, new LinkedBlockingQueue<>(1000),
        (r) -> new Thread(r, "Moho_DefaultHttpServer_Thread" + threadCounter.get()), (r, executor) -> {
    });

    private Map<String, HttpServerHandler> handlerMap = new HashMap<>();

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

            HttpServerHandler httpHandler = handlerMap.get(httpExchange.getRequestURI().getPath().split("\\.")[1]);
            HttpResponse response;
            if (null != httpHandler) {
                HttpRequest request = new HttpRequest();
                request.setPath(httpExchange.getRequestURI().getPath());
                request.setParams(buildGetParams(httpExchange.getRequestURI()));
                response = httpHandler.handle(request);
            } else {
                HttpResponse error = new HttpResponse();
                error.setContentType("text/plain;charset=utf-8");
                error.setContent("404[NO_HTTP_HANDLER_FOUND]");
                response = error;
            }

            //设置Content-Type
            Headers headers = httpExchange.getResponseHeaders();
            headers.set("Content-Type", response.getContentType());
            //设置返回内容，默认UTF-8
            byte[] bytes = response.getContent().getBytes(Charset.forName("UTF-8"));
            httpExchange.sendResponseHeaders(200, bytes.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(bytes);
            os.close();
        });

        sunHttpServer.setExecutor(this.executor);

        sunHttpServer.start();
        log.info("Moho DefaultHttpServer started. port=" + port);
    }

    @Override
    public void stop() {
        if (!isStart.compareAndSet(true, false)) {
            return;
        }

        sunHttpServer.stop(1);
        log.info("Moho DefaultHttpServer stop.");
    }

    @Override
    public void registerHandler(String key, HttpServerHandler httpHandler) {
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
