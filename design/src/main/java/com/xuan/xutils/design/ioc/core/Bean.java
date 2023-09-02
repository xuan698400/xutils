package com.xuan.xutils.design.ioc.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在Bean上打上该注解，IOC容器就会进行接管
 *
 * @author xuan
 * @since 2022/12/8
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Bean {
    String value() default "";
}
