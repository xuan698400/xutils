package com.xuan.mix.an.httpserver;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import com.xuan.mix.an.httpserver.handler.BaseHtmlHttpServerHandler;
import com.xuan.mix.an.httpserver.handler.BaseTextHttpServerHandler;
import com.xuan.mix.an.httpserver.impl.DefaultHttpServer;

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
        protected String doHandle(String path, Map<String, String> params) {
            System.out.println("mini: path:" + path + ", params:" + params);
            return "client: path:" + path + ", params:" + params;
        }
    }

    public static class TestHtmlHandler extends BaseHtmlHttpServerHandler {
        @Override
        protected String doHandle(String path, Map<String, String> params) {
            return "<span style=\"color:red;\">path:" + path + "</span>";
        }
    }

}
