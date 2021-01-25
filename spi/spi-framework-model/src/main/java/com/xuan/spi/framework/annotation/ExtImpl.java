package com.xuan.spi.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xuan
 * @since 2021/1/23
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ExtImpl {

    /**
     * @return The code of current template's extension supported.
     */
    String[] codes() default "";

    /**
     * @return The scenario of current template's extension realization.
     */
    String scenario() default "";

}
