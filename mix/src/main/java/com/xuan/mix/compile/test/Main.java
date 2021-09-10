package com.xuan.mix.compile.test;

import java.lang.reflect.Method;

import com.xuan.mix.compile.CompileConfig;
import com.xuan.mix.compile.Compiler;
import com.xuan.mix.compile.CompilerFactory;
import com.xuan.mix.compile.SourceCode;

/**
 * @author xuan
 * @since 2020/10/19
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Compiler compiler = CompilerFactory.getJdkJavaCompiler();

        SourceCode sourceCode = SourceCode.of("package com.xuan.mix.compile.test;\n"
            + "\n"
            + "/**\n"
            + " * @author xuan\n"
            + " * @since 2020/10/19\n"
            + " */\n"
            + "public class DemoTestClass {\n"
            + "    public String hello() {\n"
            + "        return \"你好太牛逼\";\n"
            + "    }\n"
            + "}\n", "com.xuan.mix.compile.test.DemoTestClass");

        Class<?> demoTestClass = compiler.compile(sourceCode, new CompileConfig());
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
