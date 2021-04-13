package com.xuan.mix.compile;

/**
 * Java编译器接口
 *
 * @author xuan
 * @since 2020/10/19
 */
public interface JavaCompiler {
    
    /**
     * 编辑Java代码，并返回其Class对象
     *
     * @param javaSource
     * @param compileOption
     * @return
     */
    Class<?> compile(JavaSource javaSource, CompileOption compileOption);
}
