package com.extp.framework.core;

import com.extp.framework.core.plugin.PluginManager;

/**
 * @author xuan
 * @since 2021/8/13
 */
public class ExtpInitializer {

    public static void init() {
        PluginManager.getInstance().init();
        FunctionManager.getInstance().init();
    }
}
