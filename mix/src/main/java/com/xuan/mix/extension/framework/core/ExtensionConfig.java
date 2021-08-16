package com.xuan.mix.extension.framework.core;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xuan
 * @since 2021/2/26
 */
public class ExtensionConfig {

    /**
     * extension包路径
     */
    private Set<String> extensionPackages = new HashSet<>();
    /**
     * plugin包路径
     */
    private Set<String> pluginPackages = new HashSet<>();

    public Set<String> getExtensionPackages() {
        return extensionPackages;
    }

    public Set<String> getPluginPackages() {
        return pluginPackages;
    }

    public void addExtensionPackage(String extensionPackage) {
        extensionPackages.add(extensionPackage);
    }

    public void addPluginPackage(String pluginPackage) {
        pluginPackages.add(pluginPackage);
    }

}
