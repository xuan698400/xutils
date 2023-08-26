package com.xuan.xutils.compiler;

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

    /**
     * 获取源代码
     *
     * @return 源代码
     */
    public String getSourceCode() {
        return sourceCode;
    }

    /**
     * 设置源代码
     *
     * @param sourceCode 源代码
     */
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    /**
     * 获取代码类的全路径
     *
     * @return 代码类的全路径
     */
    public String getFullClassName() {
        return fullClassName;
    }

    /**
     * 设置代码类的全路径
     *
     * @param fullClassName 代码类的全路径
     */
    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

}
