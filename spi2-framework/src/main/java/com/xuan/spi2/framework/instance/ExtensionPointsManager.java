package com.xuan.spi2.framework.instance;

import com.xuan.spi2.framework.config.AppConfigManager;

/**
 * @author xuan
 * @since 2020/11/16
 */
public class ExtensionPointsManager {

    private static volatile boolean initialized = false;

    private static volatile boolean finished = false;

    private static ExtensionPointsManager instance;

    public <ExtensionPoints> ExtensionPoints getExtensionPoints(Class<ExtensionPoints> extensionPointsClass,
        String bizCode) {

        return null;
    }

    public static ExtensionPointsManager getInstance() {
        if (null == instance) {
            synchronized (ExtensionPointsManager.class) {
                if (null == instance) {
                    ExtensionPointsManager extensionPointsManager = new ExtensionPointsManager();
                    try {
                        extensionPointsManager.init();
                        instance = extensionPointsManager;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return instance;
    }

    private void init() throws Exception {
        synchronized (this) {
            while (initialized && !finished) {
                wait(1000);
            }
            if (initialized && finished) {
                return;
            }

            if (!initialized) {
                initialized = true;
                AppConfigManager.getInstance().scanApp();
                finished = true;
            }
        }
    }

}
