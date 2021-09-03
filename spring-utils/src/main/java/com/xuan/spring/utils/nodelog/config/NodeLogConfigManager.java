package com.xuan.spring.utils.nodelog.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuan
 * @since 2019/11/6
 */
public class NodeLogConfigManager {

    private static Map<String, NodeLogConfig> configMap = new ConcurrentHashMap<>();

    public static NodeLogConfig getNodeLogConfig(String nodeName) {
        if (null == nodeName) {
            return NodeLogConfig.DEFAULT;
        }

        NodeLogConfig nodeLogConfig = configMap.get(nodeName);
        if (null != nodeLogConfig) {
            return nodeLogConfig;
        }

        NodeLogConfig defaultConfig = configMap.get(NodeLogConfig.NODE_NAME_DEFAULT);
        if (null != defaultConfig) {
            return defaultConfig;
        }

        return NodeLogConfig.DEFAULT;
    }

    public static void setNodeLogConfig(Map<String, NodeLogConfig> configMap) {
        NodeLogConfigManager.configMap = configMap;
    }

    public static void addNodeLogConfig(NodeLogConfig nodeLogConfig) {
        if (null == nodeLogConfig || null == nodeLogConfig.getNodeName()
            || nodeLogConfig.getNodeName().trim().length() == 0) {
            return;
        }
        configMap.put(nodeLogConfig.getNodeName(), nodeLogConfig);
    }

}
