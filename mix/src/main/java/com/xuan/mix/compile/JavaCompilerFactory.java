package com.xuan.mix.compile;

import com.xuan.mix.compile.impl.JdkJavaCompiler;

/**
 * @author xuan
 * @since 2020/10/19
 */
public class JavaCompilerFactory {

    public static JavaCompiler getJdkJavaCompiler() {
        return new JdkJavaCompiler();
    }

}
