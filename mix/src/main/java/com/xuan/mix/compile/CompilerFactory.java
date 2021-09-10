package com.xuan.mix.compile;

import com.xuan.mix.compile.java.JdkJavaCompiler;

/**
 * @author xuan
 * @since 2020/10/19
 */
public class CompilerFactory {

    public static Compiler getJdkJavaCompiler() {
        return new JdkJavaCompiler();
    }

}
