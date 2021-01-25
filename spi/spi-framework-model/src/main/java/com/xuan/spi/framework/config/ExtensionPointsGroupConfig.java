package com.xuan.spi.framework.config;

/**
 * @author xuan
 * @since 2020/11/16
 */
public class ExtensionPointsGroupConfig {

    private String clazz;

    private Integer priority;

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

}
