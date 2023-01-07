package com.xuan.moho.tools.compiler;

import com.xuan.moho.tools.compiler.java.JdkJavaCompiler;

/**
 * @author xuan
 * @since 2020/10/19
 */
public class CompilerFactory {

    public static Compiler getJdkJavaCompiler() {
        return new JdkJavaCompiler();
    }

}
