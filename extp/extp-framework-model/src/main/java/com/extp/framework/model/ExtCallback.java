package com.extp.framework.model;

/**
 * @author xuan
 * @since 2021/2/25
 */
public interface ExtCallback<Ext, RESULT> {

    /**
     * 执行扩展点
     *
     * @param ext 扩展点实例
     * @return 执行结果
     */
    RESULT apply(Ext ext);
}
