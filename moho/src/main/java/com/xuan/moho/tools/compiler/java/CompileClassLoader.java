package com.xuan.moho.tools.compiler.java;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import com.xuan.moho.tools.compiler.CompileException;

/**
 * @author xuan
 * @since 2020/10/19
 */
public class CompileClassLoader extends URLClassLoader {
    private static volatile CompileClassLoader instance = null;

    private CompileClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public static CompileClassLoader getInstance(String dirPath) {
        if (null == instance) {
            synchronized (CompileClassLoader.class) {
                if (null == instance) {
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

    public Class<?> defineClass(String name, byte[] classBytes) {
        return defineClass(name, classBytes, 0, classBytes.length);
    }

}
