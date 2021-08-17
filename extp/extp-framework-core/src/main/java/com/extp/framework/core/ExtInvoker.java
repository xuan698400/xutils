package com.extp.framework.core;

import java.util.List;

import com.extp.framework.model.BizInstance;
import com.extp.framework.model.ExtCallback;

/**
 * @author xuan
 * @since 2021/2/25
 */
public class ExtInvoker<Ext> {

    private Class<Ext> extClass;

    public ExtInvoker(Class<Ext> extClass) {
        this.extClass = extClass;
    }

    public <TARGET extends BizInstance, RESULT> RESULT executeFirst(TARGET target,
        ExtCallback<Ext, RESULT> callback) {

        List<Ext> extList = FunctionManager.getInstance().getExt(
            extClass,
            target.getBizCode());
        if (null == extList || extList.isEmpty()) {
            return null;
        }

        return callback.apply(extList.get(0));
    }

}
