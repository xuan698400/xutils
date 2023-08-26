package com.xuan.xutils.sequence;

import java.util.concurrent.CountDownLatch;

import com.alibaba.druid.pool.DruidDataSource;

import com.xuan.xutils.sequence.impl.range.RangeSequence;
import com.xuan.xutils.sequence.impl.range.impl.db.DbRangeManager;
import com.xuan.xutils.sequence.impl.range.impl.redis.RedisRangeManager;
import com.xuan.xutils.sequence.impl.snowflake.SnowflakeSequence;

/**
 * Created by xuan on 2018/5/31.
 */
public class BaseTest {

    protected final static String LOG_PATH = "/Users/xuan/Desktop/code/github/xutils/distributed-sequence/jmh-logs/";

    /**
     * 初始化DB实现的序列号生成器，参数说明如下
     * 需要一个数据源
     *
     * @param name name
     * @return Sequence
     */
    protected Sequence initDbSequence(String name) {
        //需要一个数据源
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/bpmweb?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("bpmweb");
        dataSource.setPassword("123456");
        dataSource.setMaxActive(300);
        dataSource.setMinIdle(50);
        dataSource.setInitialSize(2);
        dataSource.setMaxWait(500);
        //初始化DB区间管理器
        DbRangeManager rangeManager = new DbRangeManager();
        rangeManager.setDataSource(dataSource);
        rangeManager.setStep(1000);
        rangeManager.setStepStart(0L);
        rangeManager.init();
        //生成序列号
        RangeSequence rangeSequence = new RangeSequence();
        rangeSequence.setName(name);
        rangeSequence.setRangeManager(rangeManager);
        return rangeSequence;
    }

    /**
     * 初始化Redis实现的序列号生成器，参数说明如下：
     *
     * ip：redis连接ip
     * port：redis连接port
     * auth：如果redis设置了密码权限需要设置，没有就可以不用设置
     * bizName：具体某中业务的序列号
     * step：[可选] 默认1000，即每次取redis获取步长值，根据具体业务吞吐量来设置，越大性能越好，但是序列号断层的风险也就越大
     *
     * @param name name name
     * @return Sequence Sequence
     */
    protected Sequence initRedisSequence(String name) {
        //初始化Redis区间管理器
        RedisRangeManager rangeManager = new RedisRangeManager();
        rangeManager.setIp("localhost");
        rangeManager.setPort(8088);
        rangeManager.setAuth("auth");
        rangeManager.setStep(1000);
        rangeManager.setStepStart(0L);
        rangeManager.init();
        //生成序列号
        RangeSequence rangeSequence = new RangeSequence();
        rangeSequence.setName(name);
        rangeSequence.setRangeManager(rangeManager);
        return rangeSequence;
    }

    /**
     * 初始化雪花算法实现的序列号生成器，参数说明如下：
     *
     * datacenterId: 一般可以设置机房标志，值只能设置[0-31]之间
     * workerId: 一般设置主机编号，值只能设置[0-31]之间
     * 只用这来那个值保证服务器唯一就可以
     *
     * @return Sequence Sequence
     */
    protected Sequence initSnowflakeSequence() {
        SnowflakeSequence sequence = new SnowflakeSequence();
        sequence.setDatacenterId(1);
        sequence.setWorkerId(2);
        return sequence;
    }

    /**
     * 多线程异步执行目前先人肉看看是否对
     *
     * @param sequence sequence
     */
    protected void testRun(Sequence sequence) {
        CountDownLatch countDownLatch = new CountDownLatch(20);

        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    System.out.println(
                        "++++++++++id:" + sequence.nextValue() + ", thread:" + Thread.currentThread().getName());
                }
                countDownLatch.countDown();
            });
            thread.setName("thread-" + i);
            thread.start();
        }

        try {
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
