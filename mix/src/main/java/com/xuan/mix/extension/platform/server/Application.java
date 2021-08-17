package com.xuan.mix.extension.platform.server;

import com.xuan.mix.extension.framework.core.ExtensionConfig;
import com.xuan.mix.extension.framework.core.ExtensionManager;

/**
 * @author xuan
 * @since 2021/2/26
 */
public class Application {

    private static SayHelloService sayHelloService = new SayHelloServiceImpl();

    static {
        ExtensionConfig config = new ExtensionConfig();
        config.addExtensionPackage("com.xuan.mix.extension.platform.sdk");
        config.addPluginPackage("com.xuan.mix.extension.biz");
        ExtensionManager.getInstance().init(config);
    }

    public static void main(String[] args) {
        System.out.println(sayHelloService.sayHello("徐工", "plugin1"));
        System.out.println(sayHelloService.sayHello("徐工", "plugin1_abc"));
        System.out.println(sayHelloService.sayHello("徐工", "plugin2"));
    }

}
