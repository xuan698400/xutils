package com.xuan.mix.net.miniserver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.xuan.mix.net.miniclient.MiniClientFacade;
import com.xuan.mix.net.miniserver.handler.BaseHtmlServerHandler;
import com.xuan.mix.net.miniserver.handler.BaseTextServerHandler;
import com.xuan.mix.net.miniserver.impl.DefaultHttpServer;

/**
 * @author xuan
 * @since 2022/9/30
 */
public class Main {

    public static void main(String[] args) throws IOException {
        MiniServer httpServer = startServer();
        System.out.println("测试结束，输入任何字符停止服务");
        new Scanner(System.in).next();
        httpServer.stop();
    }

    private static MiniServer startServer() throws IOException {
        MiniServer httpServer = new DefaultHttpServer(8888);
        httpServer.registerHandler("txt", new TestTextHandler());
        httpServer.registerHandler("html", new TestHtmlHandler());
        httpServer.start();
        return httpServer;
    }

    public static class TestTextHandler extends BaseHtmlServerHandler {
        @Override
        protected String doHandle(String path, Map<String, String> params) {
            System.out.println("mini: path:" + path + ", params:" + params);
            return "client: path:" + path + ", params:" + params;
        }
    }

    public static class TestHtmlHandler extends BaseTextServerHandler {
        @Override
        protected String doHandle(String path, Map<String, String> params) {
            //return "<span style=\"color:red;\">path:" + path + "</span>";
            return MiniClientFacade.get("http://www.baidu.com", new HashMap<>()).getResultStr();
        }
    }

}
