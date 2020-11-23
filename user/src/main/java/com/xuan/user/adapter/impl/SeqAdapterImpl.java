package com.xuan.user.adapter.impl;

import com.alibaba.druid.pool.DruidDataSource;

import com.xuan.seq.DbSeqBuilder;
import com.xuan.seq.sequence.Sequence;
import com.xuan.user.adapter.SeqAdapter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @since 2020/11/23
 */
@Component
public class SeqAdapterImpl implements SeqAdapter, InitializingBean {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    private Sequence userSequence;

    @Override
    public Long createNewUserId() {
        return userSequence.nextValue();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //数据源
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(300);
        dataSource.setMinIdle(50);
        dataSource.setInitialSize(2);
        dataSource.setMaxWait(500);

        userSequence = DbSeqBuilder.create().dataSource(dataSource).step(10).stepStart(1000000).bizName("user").build();
    }

}
