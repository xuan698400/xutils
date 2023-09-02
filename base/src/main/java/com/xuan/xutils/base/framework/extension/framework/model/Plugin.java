package com.xuan.xutils.base.framework.extension.framework.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 插件注解，打了该注解的类会被识别成一个插件，会扫描该类的方法，找到实现的扩展点的实例
 *
 * @author xuan
 * @since 2021/2/26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Plugin {
    /**
     * 可支持的业务
     *
     * @return 业务身份数组
     */
    String[] supportBizCodes();
}
