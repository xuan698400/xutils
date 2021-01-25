package com.xuan.ext.platform.sdk;

import com.xuan.ext.framework.model.annotation.Ext;

/**
 * @author xuan
 * @since 2021/1/24
 */
@Ext(name = "定制我的名字", desc = "定制返回我的名字")
public interface MyNameExt {

    String name(String askName);
}
