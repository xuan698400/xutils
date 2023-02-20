package com.xuan.moho.framework.extension.platform.server;

import com.xuan.moho.framework.extension.framework.core.ExtensionConfig;
import com.xuan.moho.framework.extension.framework.core.ExtensionManager;

/**
 * @author xuan
 * @since 2021/2/26
 */
public class Application {

    private static SayHelloService sayHelloService = new SayHelloServiceImpl();

    static {
        ExtensionConfig config = new ExtensionConfig();
        config.addExtensionPackage("com.xuan.moho.framework.extension.platform.sdk");
        config.addPluginPackage("com.xuan.moho.framework.extension.biz");
        ExtensionManager.getInstance().init(config);
    }

    public void startApp() {
        System.out.println(sayHelloService.sayHello("徐工", "plugin1"));
        System.out.println(sayHelloService.sayHello("徐工", "plugin1_abc"));
        System.out.println(sayHelloService.sayHello("徐工", "plugin2"));
    }

}