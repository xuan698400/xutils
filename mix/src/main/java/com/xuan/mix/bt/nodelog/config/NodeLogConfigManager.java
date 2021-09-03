package com.xuan.mix.bt.nodelog.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuan
 * @since 2019/11/6
 */
public class NodeLogConfigManager {

    public final static String GLOBAL_CONFIG_KEY = "_global_config_";

    public final static NodeLogConfig DEFAULT_CONFIG = new NodeLogConfig();

    private static Map<String, NodeLogConfig> configMap = new ConcurrentHashMap<>();

    public static NodeLogConfig getConfig(String nodeName) {
        NodeLogConfig config = configMap.get(nodeName);
        if (null != config) {
            return config;
        }
        return configMap.get(GLOBAL_CONFIG_KEY);
    }

    public static void setAllConfig(Map<String, NodeLogConfig> configMap) {
        NodeLogConfigManager.configMap = configMap;
    }

    public static void addConfig(String nodeName, NodeLogConfig config) {
        if (null == config || null == nodeName || nodeName.trim().length() == 0) {
            return;
        }
        configMap.put(nodeName, config);
    }

}
