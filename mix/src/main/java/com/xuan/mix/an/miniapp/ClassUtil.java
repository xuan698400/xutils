package com.xuan.mix.an.miniapp;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author xuan
 * @since 2022/12/8
 */
public class ClassUtil {

    public static List<String> scanClasses(Class<?> appClazz) {
        if (null == appClazz) {
            throw new NullPointerException("appClazz is null.");
        }
        if (null == appClazz.getClassLoader()) {
            throw new NullPointerException("appClazz classLoader is null.");
        }

        return scanClazz(appClazz.getClassLoader(), appClazz.getPackage().getName());
    }

    private static List<String> scanClazz(ClassLoader classLoader, String packageName) {
        try {
            String packageResource = packageName.replace(".", "/");
            URL url = classLoader.getResource(packageResource);
            if (null == url) {
                throw new RuntimeException("url resource is null. packageResource:" + packageResource);
            }

            File root = new File(url.toURI());
            List<String> classList = new ArrayList<>();
            doScanClazz(root, packageName, classList);
            return classList;
        } catch (Exception e) {
            throw new RuntimeException("scan clazz exception.", e);
        }
    }

    private static void doScanClazz(File root, String packageName, List<String> classList) {
        File[] files = root.listFiles();
        if (null == files) {
            throw new RuntimeException("root listFiles is null.");
        }

        for (File child : files) {
            String name = child.getName();
            if (child.isDirectory()) {
                doScanClazz(child, packageName + "." + name, classList);
            } else if (name.endsWith(".class")) {
                String className = packageName + "." + name.replace(".class", "");
                classList.add(className);
            }
        }
    }

}
