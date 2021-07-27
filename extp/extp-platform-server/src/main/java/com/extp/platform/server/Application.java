package com.extp.platform.server;

import com.extp.framework.core.ExtpConfig;
import com.extp.framework.core.ExtpManager;

/**
 * @author xuan
 * @since 2021/2/26
 */
public class Application {

    private static SayHelloService sayHelloService = new SayHelloServiceImpl();

    static {
        ExtpConfig config = new ExtpConfig();
        config.addScanExtPackageName("com.extp.platform.sdk");
        config.addScanPluginPackageName("com.extp.plugin1");
        config.addScanPluginPackageName("com.extp.plugin2");
        ExtpManager.getInstance().init(config);
    }

    public static void main(String[] args) {
        System.out.println(sayHelloService.sayHello("徐工", "abc"));
        System.out.println(sayHelloService.sayHello("徐工", "ddd"));
    }

}
