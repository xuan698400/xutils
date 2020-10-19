package com.xuan.mix.compile;

/**
 * 编译接口
 *
 * @author xuan
 * @since 2020/10/19
 */
public interface JavaCompiler {

    /**
     * 编译
     *
     * @param javaSource    源代码
     * @param compileOption 编译选项
     * @return 类的Class对象
     */
    Class<?> compile(JavaSource javaSource, CompileOption compileOption);
}
