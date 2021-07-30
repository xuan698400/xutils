package com.extp.platform.server;

import java.net.URL;
import java.util.List;

import com.extp.framework.core.ExtpConfig;
import com.extp.framework.core.ExtpManager;
import com.extp.framework.core.plugin.PluginManager;
import com.extp.framework.core.utils.ResourceUtil;

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

        //try {
        //    PluginManager.getInstance().init();
        //} catch (Exception e) {
        //
        //}
    }

    public static void main(String[] args) {
        System.out.println(sayHelloService.sayHello("徐工", "abc"));
        System.out.println(sayHelloService.sayHello("徐工", "ddd"));
    }

}
