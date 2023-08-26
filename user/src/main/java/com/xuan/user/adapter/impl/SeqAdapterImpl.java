package com.xuan.user.adapter.impl;

import com.alibaba.druid.pool.DruidDataSource;

import com.xuan.user.adapter.SeqAdapter;
import com.xuan.xutils.sequence.Sequence;
import com.xuan.xutils.sequence.impl.range.RangeSequence;
import com.xuan.xutils.sequence.impl.range.impl.db.DbRangeManager;
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
        //使用DB获取区间管理器
        DbRangeManager rangeManager = new DbRangeManager();
        rangeManager.setDataSource(dataSource);
        rangeManager.setStep(1000);
        rangeManager.setStepStart(0L);
        rangeManager.init();

        RangeSequence sequence = new RangeSequence();
        sequence.setName("user");
        sequence.setRangeManager(rangeManager);

        userSequence = sequence;
    }

}
