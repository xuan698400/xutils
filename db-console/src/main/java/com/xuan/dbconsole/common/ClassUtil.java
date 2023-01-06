package com.xuan.dbconsole.common;

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

    public static List<String> scanClasses(Class<?> thisClass, String rootPackageName) {
        return scanClasses(Objects.requireNonNull(thisClass.getClassLoader()), rootPackageName);
    }

    private static List<String> scanClasses(ClassLoader classLoader, String packageName) {
        try {
            String packageResource = packageName.replace(".", "/");
            URL url = classLoader.getResource(packageResource);
            File root = new File(url.toURI());
            List<String> classList = new ArrayList<>();
            scanClassesInner(root, packageName, classList);
            return classList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void scanClassesInner(File root, String packageName, List<String> result) {
        for (File child : Objects.requireNonNull(root.listFiles())) {
            String name = child.getName();
            if (child.isDirectory()) {
                scanClassesInner(child, packageName + "." + name, result);
            } else if (name.endsWith(".class")) {
                String className = packageName + "." + name.replace(".class", "");
                result.add(className);
            }
        }
    }

}
