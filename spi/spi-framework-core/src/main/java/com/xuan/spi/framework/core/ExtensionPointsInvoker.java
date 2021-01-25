package com.xuan.spi.framework.core;

import com.xuan.spi.framework.instance.ExtensionPointsManager;

/**
 * @author xuan
 * @since 2020/11/16
 */
public class ExtensionPointsInvoker<ExtensionPoints> {

    private Class<ExtensionPoints> extensionPointsClass;

    public ExtensionPointsInvoker(Class<ExtensionPoints> extensionPointsClass) {
        this.extensionPointsClass = extensionPointsClass;
    }

    public <T extends BizRequest, R> R execute(T target, ExtensionPointsCallback<ExtensionPoints, R> callback) {

        String bizCode = target.getBizCode();

        ExtensionPoints extensionPoints = ExtensionPointsManager
            .getInstance()
            .getExtensionPoints(this.extensionPointsClass, bizCode);

        return callback.apply(extensionPoints);
    }

}
