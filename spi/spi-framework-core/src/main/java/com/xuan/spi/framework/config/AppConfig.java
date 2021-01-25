package com.xuan.spi.framework.config;

import java.util.List;

/**
 * @author xuan
 * @since 2020/11/16
 */
public class AppConfig {

    private String name;

    private String description;

    private String version;

    private String appClass;

    private List<String> supportedBizCodes;

    private List<ExtensionPointsGroupConfig> extensionPointsGroups;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppClass() {
        return appClass;
    }

    public void setAppClass(String appClass) {
        this.appClass = appClass;
    }

    public List<String> getSupportedBizCodes() {
        return supportedBizCodes;
    }

    public void setSupportedBizCodes(List<String> supportedBizCodes) {
        this.supportedBizCodes = supportedBizCodes;
    }

    public List<ExtensionPointsGroupConfig> getExtensionPointsGroups() {
        return extensionPointsGroups;
    }

    public void setExtensionPointsGroups(
        List<ExtensionPointsGroupConfig> extensionPointsGroups) {
        this.extensionPointsGroups = extensionPointsGroups;
    }

}
