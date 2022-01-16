package com.xuan.spring.utils;

import javax.annotation.Resource;

import com.xuan.mix.bt.nodelog.LogPrinter;
import com.xuan.spring.utils.extp.server.SayHelloService;
import com.xuan.spring.utils.nodelog.testservice.NodeLogService;
import com.xuan.spring.utils.nodelog.testservice.TestRequest;
import com.xuan.spring.utils.nodelog.testservice.TestResult;
import com.xuan.spring.utils.properties.MyConfiguration;
import com.xuan.spring.utils.properties.MyProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuan
 * @since 2021/7/30
 */
@RestController
@RequestMapping(value = "/")
public class TestController {

    @Resource
    private MyProperties myProperties;
    @Resource
    private MyConfiguration myConfiguration;
    @Resource
    private SayHelloService sayHelloService;
    @Resource
    private NodeLogService nodeLogService;

    @RequestMapping(value = "test")
    public String test(String fromName, String bizCode) {
        return sayHelloService.sayHello(fromName, bizCode);
    }

    /**
     * http://localhost:8080/main
     *
     * @return
     */
    @RequestMapping(value = "main")
    public String main(Integer t) {
        if (t == 1) {
            TestRequest testRequest = new TestRequest();
            testRequest.setName("xuan");
            nodeLogService.callTest(testRequest);
            return "1_ok";
        }
        return "default_ok";

    }

}
