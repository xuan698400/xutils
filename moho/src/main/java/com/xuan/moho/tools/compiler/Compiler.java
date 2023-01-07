package com.xuan.moho.tools.compiler;

/**
 * 编译器
 *
 * @author xuan
 * @since 2021/9/10
 */
public interface Compiler {

    /**
     * 编译Java代码，并返回其Class对象
     *
     * @param sourceCode 源代码
     * @param config     配置
     * @return Class对象
     */
    Class<?> compile(SourceCode sourceCode, CompileConfig config);
}
