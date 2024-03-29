package com.xuan.xutils.geek.code.sequence;

import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author xuan
 * @since 2018/1/10
 */
public class GkDistributedSequenceTest {

    public static void main(String[] args) throws SQLException {

        //1、初始化一个MySql的数据源，一般项目里都会配置，直接复用就行
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/bpmweb?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("bpmweb");
        dataSource.setPassword("123456");

        //2、构建序列号生成器并初始化
        GkDistributedSequence sequence = new GkDistributedSequence(dataSource, "GkDistributeSequenceTest17");
        sequence.init();

        //3、并发多个线程获取
        CountDownLatch countDownLatch = new CountDownLatch(20);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    try {
                        long sequenceNo = sequence.nextValue();
                        System.out.println(
                            "++++++++++id:" + sequenceNo + ", thread:" + Thread.currentThread().getName());
                    } catch (SQLException e) {
                        //Ingore
                    }
                }
                countDownLatch.countDown();
            });
            thread.setName("thread-" + i);
            thread.start();
        }

        try {
            countDownLatch.await();
            System.out.println((System.currentTimeMillis() - start) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
