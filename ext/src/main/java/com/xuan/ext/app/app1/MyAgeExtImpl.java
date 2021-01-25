package com.xuan.ext.app.app1;

import com.xuan.ext.framework.model.annotation.ExtImpl;
import com.xuan.ext.platform.sdk.MyAgeExt;

/**
 * @author xuan
 * @since 2021/1/25
 */
@ExtImpl
public class MyAgeExtImpl implements MyAgeExt {
    @Override
    public int age() {
        return 17;
    }
}
