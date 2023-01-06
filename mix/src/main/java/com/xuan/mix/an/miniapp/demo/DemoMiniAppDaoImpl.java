package com.xuan.mix.an.miniapp.demo;

import com.xuan.mix.an.miniapp.Bean;

/**
 * @author xuan
 * @since 2022/12/28
 */
@Bean
public class DemoMiniAppDaoImpl implements DemoMiniAppDao {

    @Override
    public String selectById(Long id) {
        return "id:" + id;
    }
}
