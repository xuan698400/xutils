package com.xuan.spi.framework.config;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author xuan
 * @since 2020/11/15
 */
public class AppConfigManager {
    private final static AppConfigManager INSTANCE = new AppConfigManager();

    public static AppConfigManager getInstance() {
        return INSTANCE;
    }

    private final static Map<String, AppConfig> NAME_2_APP_CONFIG_MAP = new HashMap<>();

    public void scanApp() {
        Set<URL> urlSet = getResources("spi2-app.xml");

        for (URL url : urlSet) {
            AppConfig appConfig = AppConfigReader.read(url);
            if (null != appConfig) {
                NAME_2_APP_CONFIG_MAP.put(appConfig.getName(), appConfig);
            }
        }
        System.out.println(urlSet);
        System.out.println(NAME_2_APP_CONFIG_MAP);
    }

    private Set<URL> getResources(String... resourceNames) {
        Set<URL> urlSet = new HashSet<>();

        for (String resourceName : resourceNames) {
            boolean isFind;

            //当前线程ClassLoader找
            isFind = collectResources(urlSet, resourceName, Thread.currentThread().getContextClassLoader(), false);

            // 如果没有，从PluginConfigMgr的ClassLoader找。
            if (!isFind) {
                collectResources(urlSet, resourceName, AppConfigManager.class.getClassLoader(), false);
            }

            // 最后使用系统ClassLoader找
            if (!isFind) {
                collectResources(urlSet, resourceName, null, true);
            }
        }
        return urlSet;
    }

    private boolean collectResources(Set<URL> resourceSet, String resourceName, ClassLoader classLoader,
        boolean ifLoadFromSysClassLoader) {

        if (null == resourceName || resourceName.trim().length() == 0) {
            return false;
        }

        Enumeration<URL> enumeration = null;
        try {
            if (classLoader != null) {
                enumeration = classLoader.getResources(resourceName);
            } else if (ifLoadFromSysClassLoader) {
                enumeration = ClassLoader.getSystemResources(resourceName);
            }
        } catch (IOException e) {
            //Ignore
        }

        if (null != enumeration) {
            while (enumeration.hasMoreElements()) {
                URL url = enumeration.nextElement();
                resourceSet.add(url);
            }
        }
        return null != resourceSet && !resourceSet.isEmpty();
    }

}
