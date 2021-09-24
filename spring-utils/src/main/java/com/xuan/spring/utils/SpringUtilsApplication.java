package com.xuan.spring.utils;

import javax.annotation.Resource;

import com.xuan.spring.utils.properties.MyProperties;
import com.xuan.spring.utils.scope.ScopeBean;
import com.xuan.spring.utils.scope.ScopeTest;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.SimpleThreadScope;

/**
 * @author xuan
 * @since 2021/7/30
 */
@SpringBootApplication
public class SpringUtilsApplication {

    @Resource
    private MyProperties myProperties;

    public static void main(String[] args) {
        SpringApplication.run(SpringUtilsApplication.class, args);
        ScopeTest.test();
    }

}
