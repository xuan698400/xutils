package com.xuan.spi2.platform.server;

import com.xuan.spi2.framework.config.AppConfigManager;

/**
 * @author xuan
 * @since 2020/11/16
 */
public class MainTest {

    static {
        AppConfigManager.getInstance().scanApp();
    }

    public static void main(String[] args) {
    }
}
