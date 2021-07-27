package com.extp.framework.core;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xuan
 * @since 2021/2/26
 */
public class ExtpConfig {

    /**
     * Ext接口扫描包名
     */
    private Set<String> scanExtPackageNameSet = new HashSet<>();
    /**
     * 插件扫描包名
     */
    private Set<String> scanPluginPackageNameSet = new HashSet<>();

    public Set<String> getScanExtPackageNameSet() {
        return scanExtPackageNameSet;
    }

    public Set<String> getScanPluginPackageNameSet() {
        return scanPluginPackageNameSet;
    }

    public void addScanExtPackageName(String scanExtPackageName) {
        scanExtPackageNameSet.add(scanExtPackageName);
    }

    public void addScanPluginPackageName(String scanPluginPackageName) {
        scanPluginPackageNameSet.add(scanPluginPackageName);
    }

}
