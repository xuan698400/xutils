package com.xuan.mix.http.mini;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 一个超级简单的Http客户端，目前只支持GET请求
 *
 * @author xuan
 * @since 2020/10/16
 */
public class MiniHttpClient {

    public static String get(String urlStr, Map<String, String> params) {
        ByteArrayOutputStream baos = null;
        InputStream is = null;
        try {
            URL url = new URL(urlStr + buildKv(params));
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //超时设置
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

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

    /**
     * 构建参数串，例如：a=b&c=d
     *
     * @return 字符串
     */
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
