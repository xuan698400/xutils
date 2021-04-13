package com.xuan.mix.compile;

import java.lang.reflect.Method;

/**
 * @author xuan
 * @since 2020/10/19
 */
public class DemoTest {

    public static void main(String[] args) throws Exception {
        JavaCompiler javaCompiler = JavaCompilerFactory.getJdkJavaCompiler();

        JavaSource javaSource = JavaSource.of("package com.xuan.mix.compile;\n"
            + "\n"
            + "/**\n"
            + " * @author xuan\n"
            + " * @since 2020/10/19\n"
            + " */\n"
            + "public class DemoTestClass {\n"
            + "    public String hello() {\n"
            + "        return \"你好太牛逼\";\n"
            + "    }\n"
            + "}", "com.xuan.mix.compile.DemoTestClass");

        Class<?> demoTestClass = javaCompiler.compile(javaSource, new CompileOption());
        System.out.println(demoTestClass.getName());

        //返回获取调用方法
        Method method = demoTestClass.getMethod("hello");
        //执行调用
        Object d = method.invoke(demoTestClass.newInstance());
        //输出结果
        System.out.println(d);
        System.out.println(demoTestClass.getClassLoader());
    }

}
