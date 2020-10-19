package com.xuan.mix.compile.impl;

import java.io.IOException;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

/**
 * @author xuan
 * @since 2020/10/19
 */
public class StringJavaFileObject extends SimpleJavaFileObject {
    private final String code;

    StringJavaFileObject(String fullClassName, String code) {
        super(URI.create("string:///" + fullClassName.replace('.', '/') + Kind.SOURCE.extension),
            Kind.SOURCE);
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return code;
    }

}
