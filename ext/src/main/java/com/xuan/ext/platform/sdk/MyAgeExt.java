package com.xuan.ext.platform.sdk;

import com.xuan.ext.framework.model.annotation.Ext;

/**
 * @author xuan
 * @since 2021/1/24
 */
@Ext(name = "定制我的年龄", desc = "定制返回我的年龄")
public interface MyAgeExt {
    int age();
}
