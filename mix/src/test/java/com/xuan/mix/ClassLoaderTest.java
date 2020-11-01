package com.xuan.mix;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author xuan
 * @since 2020/10/21
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {
        URLClassLoader loader = new URLClassLoader(
            new URL[] {new URL("file:/Users/xuan/Desktop/code/github/xutils/mix/target/test-classes/")});

        Class<?> myClass = loader.loadClass("com.xuan.mix.AppTest");

        Method method = myClass.getMethod("test");
        method.invoke(myClass.newInstance());
    }

    class MyClassLoader extends URLClassLoader {

        public MyClassLoader(URL[] urls) {
            super(urls);
        }
    }
}
