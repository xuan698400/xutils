package com.extp.platform.server;

import com.extp.framework.core.ExtpInitializer;

/**
 * @author xuan
 * @since 2021/2/26
 */
public class Application {

    private static SayHelloService sayHelloService = new SayHelloServiceImpl();

    static {
        ExtpInitializer.getInstance().init();
    }

    public static void main(String[] args) {
        System.out.println(sayHelloService.sayHello("徐工", "plugin1"));
        System.out.println(sayHelloService.sayHello("徐工", "plugin1_abc"));
        System.out.println(sayHelloService.sayHello("徐工", "plugin2"));
    }

}
