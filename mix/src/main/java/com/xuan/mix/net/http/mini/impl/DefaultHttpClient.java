package com.xuan.mix.net.http.mini.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

import com.xuan.mix.net.http.mini.HttpClient;

/**
 * @author xuan
 * @since 2022/6/23
 */
public class DefaultHttpClient implements HttpClient {

    private int connectTimeout = 100000;
    private int readTimeout = 100000;

    @Override
    public String get(String urlStr, Map<String, String> params) {
        ByteArrayOutputStream baos = null;
        InputStream is = null;
        try {
            URL url = new URL(urlStr + buildKv(params));
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);

            baos = new ByteArrayOutputStream();
            is = conn.getInputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            byte[] data = baos.toByteArray();
            return new String(data, Charset.forName("utf-8"));
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            try {
                if (null != baos) {
                    baos.close();
                }
                if (null != is) {
                    is.close();
                }
            } catch (Exception e) {
                //Ignore
            }
        }
    }

    @Override
    public int getConnectTimeout() {
        return connectTimeout;
    }

    @Override
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    @Override
    public int getReadTimeout() {
        return readTimeout;
    }

    @Override
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    private static String buildKv(Map<String, String> params) {
        if (null == params || params.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (Entry<String, String> entry : params.entrySet()) {
            String v = null;
            try {
                v = URLEncoder.encode(entry.getValue(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                //Ignore
            }
            if (null != v) {
                sb.append(entry.getKey()).append("=").append(v).append("&");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return "?" + sb.toString();
    }

}
