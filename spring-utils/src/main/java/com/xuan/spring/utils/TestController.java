package com.xuan.spring.utils;

import javax.annotation.Resource;

import com.xuan.spring.utils.properties.MyConfiguration;
import com.xuan.spring.utils.properties.MyProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuan
 * @since 2021/7/30
 */
@RestController
@RequestMapping(value = "/test/")
public class TestController {

    @Resource
    private MyProperties myProperties;
    @Resource
    private MyConfiguration myConfiguration;

    @RequestMapping(value = "test")
    public String test() {
        return "say:" + myConfiguration.getAb();
    }
}
