package com.xuan.mix.http.server;

import java.io.IOException;
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
        MiniHttpServer httpServer = new MiniHttpServer(7002, new MiniHttpHandler() {
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
