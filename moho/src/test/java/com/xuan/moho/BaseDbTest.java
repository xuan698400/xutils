package com.xuan.moho;

import com.alibaba.druid.pool.DruidDataSource;

import org.junit.Before;

/**
 * Created by xuan on 2018/5/31.
 */
public class BaseDbTest extends BaseTest {

    protected DruidDataSource dataSource;

    @Before
    public void init() {
        dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/bpmweb?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("bpmweb");
        dataSource.setPassword("123456");
        dataSource.setMaxActive(300);
        dataSource.setMinIdle(50);
        dataSource.setInitialSize(2);
        dataSource.setMaxWait(500);
    }

}
