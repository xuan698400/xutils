package com.xuan.spring.utils.nodelog.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 节点跟踪日志
 *
 * @author xuan
 * @since 19/10/30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NodeLog {

    int sample() default 10000;

    boolean printParams() default true;

    boolean printResult() default true;

    boolean printIfResultFail() default true;
}
