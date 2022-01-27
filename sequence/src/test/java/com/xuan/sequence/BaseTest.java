package com.xuan.sequence;

import java.util.concurrent.CountDownLatch;

import com.alibaba.druid.pool.DruidDataSource;

import com.xuan.sequence.range.db.DbSeqRangeMgr;
import com.xuan.sequence.range.redis.RedisSeqRangeMgr;
import com.xuan.sequence.core.DefaultRangeSequence;
import com.xuan.sequence.core.SnowflakeSequence;

/**
 * Created by xuan on 2018/5/31.
 */
public class BaseTest {

    private static DruidDataSource dataSource;
    private static DbSeqRangeMgr dbSeqRangeMgr;
    private static RedisSeqRangeMgr redisSeqRangeMgr;

    static {
        //数据源
        dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/bpmweb?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("bpmweb");
        dataSource.setPassword("123456");
        dataSource.setMaxActive(300);
        dataSource.setMinIdle(50);
        dataSource.setInitialSize(2);
        dataSource.setMaxWait(500);

        //使用DB获取区间管理器
        dbSeqRangeMgr = new DbSeqRangeMgr();
        dbSeqRangeMgr.setDataSource(dataSource);
        dbSeqRangeMgr.setStep(1000);
        dbSeqRangeMgr.setStepStart(0L);
        dbSeqRangeMgr.init();

        //利用Redis获取区间管理器
        redisSeqRangeMgr = new RedisSeqRangeMgr();
        redisSeqRangeMgr.setIp("localhost");
        redisSeqRangeMgr.setPort(8088);
        redisSeqRangeMgr.setAuth("auth");
        redisSeqRangeMgr.setStep(1000);
        redisSeqRangeMgr.setStepStart(0L);
        //redisSeqRangeMgr.init();
    }

    protected Sequence getRedisSequence(String bizName) {
        /**
         * 参数说明如下：
         * ip：redis连接ip
         * port：redis连接port
         * auth：如果redis设置了密码权限需要设置，没有就可以不用设置
         * bizName：具体某中业务的序列号
         * step：[可选] 默认1000，即每次取redis获取步长值，根据具体业务吞吐量来设置，越大性能越好，但是序列号断层的风险也就越大
         */
        DefaultRangeSequence sequence = new DefaultRangeSequence();
        sequence.setName(bizName);
        sequence.setSeqRangeMgr(redisSeqRangeMgr);
        return sequence;
    }

    protected Sequence getSnowflakeSequence() {
        /**
         * 参数说明如下：
         * datacenterId: 一般可以设置机房标志，值只能设置[0-31]之间
         * workerId: 一般设置主机编号，值只能设置[0-31]之间
         * 只用这来那个值保证服务器唯一就可以
         */
        SnowflakeSequence sequence = new SnowflakeSequence();
        sequence.setDatacenterId(1);
        sequence.setWorkerId(2);
        return sequence;
    }

    protected Sequence getDbSequence(String bizName) {
        DefaultRangeSequence sequence = new DefaultRangeSequence();
        sequence.setName(bizName);
        sequence.setSeqRangeMgr(dbSeqRangeMgr);
        return sequence;
    }

    protected void test(Sequence sequence) {
        CountDownLatch countDownLatch = new CountDownLatch(20);

        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    System.out.println(
                        "++++++++++id:" + sequence.nextValue() + "thread:" + Thread.currentThread().getName());
                }
                countDownLatch.countDown();
            });
            thread.setName("thread-" + i);
            thread.start();
        }

        try {
            countDownLatch.await();
        } catch (Exception e) {

        }
    }

}
