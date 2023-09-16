package com.xuan.xutils.geek.code.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author xuan
 * @since 2023/9/11
 */
public class GkHttpGet {

    private final static int OK = 200;

    /**
     * 缓存字节数
     */
    private static final int BUFFER_SIZE = 1024;

    /**
     * 返回数据编码
     */
    private String encode = "utf-8";

    /**
     * 连接超时，默认30S
     */
    private int connectionTimeout = 1000 * 30;

    /**
     * 读取超时，默认30S
     */
    private int readTimeout = 1000 * 30;

    public String get(String urlStr) {
        URL url;
        HttpURLConnection conn;
        try {
            //构建url
            url = new URL(urlStr);

            //获取connect对象
            conn = (HttpURLConnection)url.openConnection();

            //设置超时
            conn.setConnectTimeout(connectionTimeout);
            conn.setReadTimeout(readTimeout);

            //获取返回
            return readResponseForString(conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String readResponseForString(HttpURLConnection conn) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        InputStream inStream = null;
        try {
            inStream = conn.getInputStream();

            if (OK != conn.getResponseCode()) {
                throw new RuntimeException(String.format("response fail. responseCode[%s], responseMessage[%s]",
                    conn.getResponseCode(), conn.getResponseMessage()));
            }

            byte[] buffer = new byte[BUFFER_SIZE];
            int len;
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            byte[] data = outStream.toByteArray();

            return new String(data, encode);
        } finally {
            closeQuietly(outStream);
            closeQuietly(inStream);
        }
    }

    private void closeQuietly(AutoCloseable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Throwable e) {
                //Ignore
            }
        }
    }

}
