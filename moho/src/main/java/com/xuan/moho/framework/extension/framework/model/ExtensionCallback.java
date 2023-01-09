package com.xuan.moho.framework.extension.framework.model;

/**
 * @author xuan
 * @since 2021/2/25
 */
public interface ExtensionCallback<Extension, RESULT> {

    /**
     * 执行扩展点
     *
     * @param extension 扩展点实例
     * @return 执行结果
     */
    RESULT apply(Extension extension);
}
