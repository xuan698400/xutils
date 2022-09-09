package com.xuan.distributed.tools.sequence;

import java.util.concurrent.CountDownLatch;

import com.xuan.distributed.tools.BaseTest;
import com.xuan.distributed.tools.sequence.core.DbSequence;

/**
 * @author xuan
 * @since 2022/9/9
 */
public class Main extends BaseTest {

    public static void main(String[] args) {
        //
        DbSequence dbSequence = new DbSequence();
        dbSequence.setDataSource(getDataSource());
        dbSequence.setName("dt_sequence");
        dbSequence.init();
        //
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            submit(() -> {
                for (int j = 0; j < 100; j++) {
                    System.out.println(
                        "++++++++++id:" + dbSequence.nextValue() + "thread:" + Thread.currentThread().getName());
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
