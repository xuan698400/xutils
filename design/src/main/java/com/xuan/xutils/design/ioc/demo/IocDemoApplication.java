package com.xuan.xutils.design.ioc.demo;

import javax.annotation.Resource;

import com.xuan.xutils.design.ioc.core.Bean;
import com.xuan.xutils.design.ioc.core.BeanFactory;
import com.xuan.xutils.design.ioc.core.IocApplication;

/**
 * 模拟App启动
 *
 * @author xuan
 * @since 2023/9/2
 */
@Bean
public class IocDemoApplication {

    public static void main(String[] args) {
        IocApplication.run(IocDemoApplication.class, args);

        //模拟从工厂类获取Bean进行调用
        IocDemoService iocDemoService = BeanFactory.getBean(IocDemoService.class);
        String result = iocDemoService.getById(123L);
        System.out.println(result);
    }

}
