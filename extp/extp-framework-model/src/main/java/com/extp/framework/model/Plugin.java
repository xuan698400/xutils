package com.extp.framework.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 插件注解
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

    /**
     * 可支持的场景
     *
     * @return 场景数组
     */
    String[] supportScenarios();
}
