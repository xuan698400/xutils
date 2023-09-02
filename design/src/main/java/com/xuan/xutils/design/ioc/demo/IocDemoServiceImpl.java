package com.xuan.xutils.design.ioc.demo;

import javax.annotation.Resource;

import com.xuan.xutils.design.ioc.core.ApplicationContext;
import com.xuan.xutils.design.ioc.core.Bean;
import com.xuan.xutils.design.ioc.core.InitBean;

/**
 * @author xuan
 * @since 2022/12/28
 */
@Bean
public class IocDemoServiceImpl implements IocDemoService, InitBean {

    @Resource
    private IocDemoDao iocDemoDao;

    @Override
    public void init(ApplicationContext context) {
        System.out.println("IocDemoServiceImpl 初始化");
    }

    @Override
    public String getById(Long id) {
        return iocDemoDao.selectById(id);
    }

}
