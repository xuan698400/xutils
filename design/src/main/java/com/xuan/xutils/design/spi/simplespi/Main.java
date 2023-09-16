package com.xuan.xutils.design.spi.simplespi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author xuan
 * @since 2023/9/16
 */
public class Main {

    private static ServiceLoader<SimpleSpi> SERVICE_LOADER = ServiceLoader.load(SimpleSpi.class);

    public static void main(String[] args) {
        Iterator<SimpleSpi> iterator = SERVICE_LOADER.iterator();
        iterator.forEachRemaining(simpleSpi -> simpleSpi.execute());
    }

}
