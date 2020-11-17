package com.xuan.spi2.framework.core;

/**
 * @author xuan
 * @since 2020/11/16
 */
public interface ExtensionPointsCallback<ExtensionPoints, Result> {
    Result apply(ExtensionPoints extensionPoints);
}
