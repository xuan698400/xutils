package com.xuan.xutils.net.http.server;

import java.io.IOException;
import java.util.Scanner;

import com.xuan.xutils.net.http.server.core.DefaultHttpServer;

/**
 * @author xuan
 * @since 2022/10/2
 */
public class Main {

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = startServer();
        System.out.println("测试结束，输入任何字符停止服务");
        new Scanner(System.in).next();
        httpServer.stop();
    }

    private static HttpServer startServer() throws IOException {
        HttpServer httpServer = new DefaultHttpServer(8888);
        httpServer.registerHandler("txt", new TestTextHandler());
        httpServer.registerHandler("html", new TestHtmlHandler());
        httpServer.start();
        return httpServer;
    }

    public static class TestTextHandler extends BaseTextHttpServerHandler {
        @Override
        protected String doHandle(HttpRequest request) {
            System.out.println("mini: path:" + request.getPath() + ", params:" + request.getParams());
            return "client: path:" + request.getPath() + ", params:" + request.getParams();
        }
    }

    public static class TestHtmlHandler extends BaseHtmlHttpServerHandler {
        @Override
        protected String doHandle(HttpRequest request) {
            return "<span style=\"color:red;\">path:" + request.getPath() + "</span>";
        }
    }

}
