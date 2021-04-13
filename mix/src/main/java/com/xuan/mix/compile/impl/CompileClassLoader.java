package com.xuan.mix.compile.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import com.xuan.mix.compile.CompileException;

/**
 * 自定义类加载器
 *
 * @author xuan
 * @since 2020/10/19
 */
class CompileClassLoader extends URLClassLoader {
    private static volatile CompileClassLoader instance = null;

    private CompileClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    static CompileClassLoader getInstance(String dirPath) {
        if (instance == null) {
            synchronized (CompileClassLoader.class) {
                if (instance == null) {
                    try {
                        URL url = new URL("file:///" + dirPath);
                        instance = new CompileClassLoader(new URL[] {url},
                            CompileClassLoader.class.getClassLoader());
                    } catch (MalformedURLException e) {
                        throw new CompileException(e.getMessage(), e);
                    }
                }
            }
        }
        return instance;
    }

    Class<?> defineClass(String name, byte[] classBytes) {
        return defineClass(name, classBytes, 0, classBytes.length);
    }

}
