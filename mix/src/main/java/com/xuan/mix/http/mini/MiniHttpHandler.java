package com.xuan.mix.http.mini;

import java.util.Map;

/**
 * @author xuan
 * @since 2020/10/16
 */
public interface MiniHttpHandler {

    String handle(String path, Map<String, String> params);
}
