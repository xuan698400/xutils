package com.xuan.xutils.geek.code.compiler;

import java.lang.reflect.Method;

import org.junit.Test;

/**
 * @author xuan
 * @since 2023/1/7
 */
public class GkJavaCompilerTest {

    @Test
    public void test() throws Exception {
        GkJavaCompiler gkJavaCompiler = new GkJavaCompiler("/Users/xuan/Downloads/gk_compiler_cache_dir");

        String fullClassName = "com.xuan.xutils.geek.code.compiler.DemoTestClass";

        String javaCode = "package com.xuan.xutils.geek.code.compiler;\n"
            + "\n"
            + "/**\n"
            + " * @author xuan\n"
            + " * @since 2020/10/19\n"
            + " */\n"
            + "public class DemoTestClass {\n"
            + "    public String hello() {\n"
            + "        return \"你好太牛逼\";\n"
            + "    }\n"
            + "}";

        Class<?> demoTestClass = gkJavaCompiler.compile(fullClassName, javaCode);

        System.out.println(demoTestClass.getName());

        //反射调用
        Method method = demoTestClass.getMethod("hello");
        Object d = method.invoke(demoTestClass.newInstance());
        System.out.println(d);
        System.out.println(demoTestClass.getClassLoader());
    }

}
