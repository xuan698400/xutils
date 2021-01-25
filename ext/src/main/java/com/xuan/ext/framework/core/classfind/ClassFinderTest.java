package com.xuan.ext.framework.core.classfind;

import java.util.Set;

/**
 * @author xuan
 * @since 2021/1/25
 */
public class ClassFinderTest {

    private static ClassFinder classFinder = new ClassFinderImpl();

    public static void main(String[] args) {
        getExt();
    }

    public static void getExt() {
        Set<Class> classSet = classFinder.getExtClasses("com.xuan.ext.platform.sdk");

        for (Class c : classSet) {
            System.out.println(c.getName());
        }
    }

    public static void getAll() {
        Set<Class> classSet = classFinder.getAllClasses(null, "com.xuan.ext.framework");

        for (Class c : classSet) {
            System.out.println(c.getName());
        }
    }
}
