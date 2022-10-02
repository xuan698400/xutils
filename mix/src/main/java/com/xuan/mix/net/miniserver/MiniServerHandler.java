package com.xuan.mix.net.miniserver;

import java.util.Map;

/**
 * @author xuan
 * @since 2022/6/23
 */
public interface MiniServerHandler {

    MiniServerResponse handle(String path, Map<String, String> params);
}
