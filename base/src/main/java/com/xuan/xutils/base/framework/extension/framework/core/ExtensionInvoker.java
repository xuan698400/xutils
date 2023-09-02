package com.xuan.xutils.base.framework.extension.framework.core;

import java.util.List;

import com.xuan.xutils.base.framework.extension.framework.model.BizInstance;
import com.xuan.xutils.base.framework.extension.framework.model.ExtensionCallback;

/**
 * @author xuan
 * @since 2021/2/25
 */
public class ExtensionInvoker<Extension> {

    private Class<Extension> extensionClass;

    public ExtensionInvoker(Class<Extension> extensionClass) {
        this.extensionClass = extensionClass;
    }

    public <TARGET extends BizInstance, RESULT> RESULT executeFirst(TARGET target,
        ExtensionCallback<Extension, RESULT> callback) {

        List<Extension> extensionList = ExtensionManager.getInstance().getExtensions(
            extensionClass,
            target.getBizCode());
        if (null == extensionList || extensionList.isEmpty()) {
            return null;
        }

        return callback.apply(extensionList.get(0));
    }

}
