package com.xuan.mix.net.http.mini;

import java.util.Map;

/**
 * @author xuan
 * @since 2022/6/23
 */
public interface HttpClient {

    String get(String url, Map<String, String> params);

    int getConnectTimeout();

    void setConnectTimeout(int connectTimeout);

    int getReadTimeout();

    void setReadTimeout(int readTimeout);
}
