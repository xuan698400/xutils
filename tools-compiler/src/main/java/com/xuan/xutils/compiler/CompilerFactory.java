package com.xuan.xutils.compiler;

import com.xuan.xutils.compiler.impl.java.JdkJavaCompiler;

/**
 * @author xuan
 * @since 2020/10/19
 */
public class CompilerFactory {

    public static Compiler getJdkJavaCompiler() {
        return new JdkJavaCompiler();
    }

}
