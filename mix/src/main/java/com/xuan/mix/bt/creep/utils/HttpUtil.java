package com.xuan.mix.bt.creep.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author xuan
 * @since 2020/10/23
 */
public class HttpUtil {

    public static String get(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        //设置GET方式请求
        connection.setRequestMethod("GET");
        //设置可以向服务器收发信息
        connection.setDoInput(true);
        connection.setDoOutput(true);
        //设置超时
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(60000);
        //header
        connection.addRequestProperty("user-agent",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183"
                + ".121 Safari/537.36");

        if (connection.getResponseCode() == 200) {
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
                sb.append("\r\n");
            }
            return sb.toString();
        }
        return connection.getResponseCode() + ":" + connection.getResponseMessage();
    }

}
