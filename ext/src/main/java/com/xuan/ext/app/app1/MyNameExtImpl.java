package com.xuan.ext.app.app1;

import com.xuan.ext.framework.model.annotation.ExtImpl;
import com.xuan.ext.platform.sdk.MyNameExt;

/**
 * @author xuan
 * @since 2021/1/25
 */
@ExtImpl
public class MyNameExtImpl implements MyNameExt {
    @Override
    public String name(String askName) {
        return String.format("你好！%s，我叫：app1", askName);
    }
}
