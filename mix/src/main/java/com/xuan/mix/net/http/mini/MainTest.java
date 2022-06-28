package com.xuan.mix.net.http.mini;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.xuan.mix.net.http.mini.handler.TextPlainHttpHandler;
import com.xuan.mix.net.http.mini.impl.DefaultHttpClient;
import com.xuan.mix.net.http.mini.impl.DefaultHttpServer;

/**
 * @author xuan
 * @since 2022/6/23
 */
public class MainTest {

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = initHttpServer();
        clientTest();

        System.out.println("测试结束，输入任何字符停止服务");
        new Scanner(System.in).next();
        httpServer.stop();
    }

    private static HttpServer initHttpServer() throws IOException {
        HttpServer httpServer = new DefaultHttpServer(8888);
        httpServer.registerHandler(".txt", new TestHandler());

        httpServer.start();
        return httpServer;
    }

    public static class TestHandler extends TextPlainHttpHandler {
        @Override
        protected String doHandle(String path, Map<String, String> params) {
            System.out.println("mini: path:" + path + ", params:" + params);
            return "client: path:" + path + ", params:" + params;
        }
    }

    private static void clientTest() {
        HttpClient httpClient = new DefaultHttpClient();

        for (int i = 0; i < 10; i++) {
            Map<String, String> params = new HashMap<>();
            params.put("i", String.valueOf(i));
            params.put("name", "中文");
            String r = httpClient.get("http://localhost:8888/dddd/rrr", params);
            System.out.println(r);
        }
    }

}
