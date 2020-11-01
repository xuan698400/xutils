package com.xuan.mix.compile;

/**
 * @author xuan
 * @since 2020/10/19
 */
public class DemoTest {

    public static void main(String[] args) {
        JavaCompiler javaCompiler = JavaCompilerFactory.getJdkJavaCompiler();

        JavaSource javaSource = JavaSource.of("package com.xuan.mix.compile;\n"
            + "\n"
            + "/**\n"
            + " * @author xuan\n"
            + " * @since 2020/10/19\n"
            + " */\n"
            + "public class DemoTest {\n"
            + "\n"
            + "    public static void main(String[] args) {\n"
            + "        JavaCompiler javaCompiler = JavaCompilerFactory.getJdkJavaCompiler();\n"
            + "\n"
            + "        JavaSource javaSource = JavaSource.of(\"\", \"com.xuan.mix.compile.DemoTest\");\n"
            + "\n"
            + "        Class<?> demoTestClass = javaCompiler.compile(javaSource, new CompileOption());\n"
            + "        System.out.println(demoTestClass.getName());\n"
            + "    }\n"
            + "\n"
            + "}\n", "com.xuan.mix.compile.DemoTest");

        Class<?> demoTestClass = javaCompiler.compile(javaSource, new CompileOption());
        System.out.println(demoTestClass.getName());
    }

    public String hello() {
        return "???????";
    }
    
}