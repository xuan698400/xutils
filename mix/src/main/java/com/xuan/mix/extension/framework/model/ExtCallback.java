package com.xuan.mix.extension.framework.model;

/**
 * @author xuan
 * @since 2021/2/25
 */
public interface ExtCallback<Extension, RESULT> {

    /**
     * 执行扩展点
     *
     * @param extensionInstance 扩展点实例
     * @return 执行结果
     */
    RESULT apply(Extension extensionInstance);
}
