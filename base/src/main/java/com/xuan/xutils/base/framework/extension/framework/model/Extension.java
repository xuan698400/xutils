package com.xuan.xutils.base.framework.extension.framework.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 扩展点注解，扩展点一般是一个接口，只有打上了这个注解才会被扫描到，并识别成扩展点
 *
 * @author xuan
 * @since 2021/2/26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Extension {
}
