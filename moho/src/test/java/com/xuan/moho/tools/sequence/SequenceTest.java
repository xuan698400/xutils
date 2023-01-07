package com.xuan.moho.tools.sequence;

import java.util.concurrent.CountDownLatch;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;

import com.xuan.moho.DataSourceFactory;
import com.xuan.moho.ThreadExecutor;
import com.xuan.moho.tools.sequence.db.DbSequence;
import org.junit.Before;
import org.junit.Test;

/**
 * @author xuan
 * @since 2023/1/7
 */
public class SequenceTest {

    private Sequence sequence;

    @Before
    public void initDao() {
        DataSource dataSource = DataSourceFactory.getDataSource();
        sequence = DbSequence.builder().name("SequenceTest").dataSource(dataSource).build();
    }

    @Test
    public void testNext() {
        //
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            ThreadExecutor.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    System.out.println(
                        "++++++++++id:" + sequence.nextValue() + "thread:" + Thread.currentThread().getName());
                }
                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
        } catch (Exception e) {
            //Ignore
        }
        System.out.println("done");
    }

}
