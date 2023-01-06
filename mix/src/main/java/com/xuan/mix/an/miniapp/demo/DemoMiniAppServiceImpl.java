package com.xuan.mix.an.miniapp.demo;

import javax.annotation.Resource;

import com.xuan.mix.an.miniapp.ApplicationContext;
import com.xuan.mix.an.miniapp.Bean;
import com.xuan.mix.an.miniapp.InitBean;

/**
 * @author xuan
 * @since 2022/12/28
 */
@Bean
public class DemoMiniAppServiceImpl implements DemoMiniAppService, InitBean {

    @Resource
    private DemoMiniAppDao demoMiniAppDao;

    @Override
    public void init(ApplicationContext context) {
        System.out.println("Bean 初始化");
    }

    @Override
    public String getById(Long id) {
        return demoMiniAppDao.selectById(id);
    }

}
