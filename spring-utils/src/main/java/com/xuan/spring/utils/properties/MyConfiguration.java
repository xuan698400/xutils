package com.xuan.spring.utils.properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuan
 * @since 2021/7/30
 */
@Configuration
public class MyConfiguration {

    @Resource
    private MyProperties myProperties;

    public String getAb() {
        return myProperties.getA1() + myProperties.getB1();
    }

}
