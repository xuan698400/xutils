package com.xuan.xutils.design.ioc.demo;

import com.xuan.xutils.design.ioc.core.ApplicationContext;
import com.xuan.xutils.design.ioc.core.Bean;
import com.xuan.xutils.design.ioc.core.InitBean;

/**
 * @author xuan
 * @since 2022/12/28
 */
@Bean
public class IocDemoDaoImpl implements IocDemoDao, InitBean {

    @Override
    public void init(ApplicationContext context) {
        System.out.println("IocDemoDaoImpl 初始化");
    }

    @Override
    public String selectById(Long id) {
        return "id:" + id;
    }

}
