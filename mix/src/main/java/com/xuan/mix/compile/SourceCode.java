package com.xuan.mix.compile;

/**
 * 源代码
 *
 * @author xuan
 * @since 2021/9/10
 */
public class SourceCode {
    /**
     * 源代码
     */
    private String sourceCode;
    /**
     * 类全路径，例如：com.xuan.mix.compile.test.DemoTestClass
     * 如果是java源代码必须要指定
     */
    private String fullClassName;

    public static SourceCode of(String sourceCode, String fullClassName) {
        SourceCode sc = new SourceCode();
        sc.setSourceCode(sourceCode);
        sc.setFullClassName(fullClassName);
        return sc;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

}
