package com.xuan.mix.jdk.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author xuan
 * @since 2022/11/18
 */
public class Main {

    private static ServiceLoader<ExtSpi> serviceLoader = ServiceLoader.load(ExtSpi.class);

    public static void main(String[] args) {
        Iterator<ExtSpi> iterator1 = serviceLoader.iterator();
        iterator1.forEachRemaining(extSpi -> {
            if (extSpi.support("bizCode1")) {
                String accept = extSpi.execute("xuan");
                System.out.println(accept);
            }
        });

        Iterator<ExtSpi> iterator2 = serviceLoader.iterator();
        iterator2.forEachRemaining(extSpi -> {
            if (extSpi.support("bizCode2")) {
                String accept = extSpi.execute("xuan");
                System.out.println(accept);
            }
        });
    }

}
