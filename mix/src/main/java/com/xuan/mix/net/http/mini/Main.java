package com.xuan.mix.net.http.mini;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * http://localhost:7002/dddd/rrr?a=xxx&b=ccc&a=123
 *
 * @author xuan
 * @since 2021/8/16
 */
public class Main {

    public static void main(String[] args) throws IOException {
        MiniHttpServer server = serverTest();
        clientTest();

        System.out.println("测试结束，输入任何字符停止服务");
        new Scanner(System.in).next();
        server.stop();
    }

    private static MiniHttpServer serverTest() throws IOException {
        MiniHttpServer httpServer = new MiniHttpServer(7002, new MiniHttpHandler() {
            @Override
            public String handle(String path, Map<String, String> params) {
                System.out.println("server: path:" + path + ", params:" + params);
                return "client: path:" + path + ", params:" + params;
            }
        });

        httpServer.start();
        return httpServer;
    }

    private static void clientTest() {
        for (int i = 0; i < 10; i++) {
            Map<String, String> params = new HashMap<>();
            params.put("i", String.valueOf(i));
            params.put("name", "中文");
            String r = MiniHttpClient.get("http://localhost:7002/dddd/rrr", params);
            System.out.println(r);
        }
    }

}
