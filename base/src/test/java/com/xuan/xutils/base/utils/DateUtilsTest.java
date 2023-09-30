package com.xuan.xutils.base.utils;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

/**
 * @author xuan
 * @since 2023/9/28
 */
public class DateUtilsTest {

    private Date temDate = new Date();

    @Test
    public void testAddDay() throws Exception {

        logDate(temDate);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    Date d = DateUtils.addDay(temDate, 1);
                    logDate(d);
                }
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        System.out.println("测试结束!!!");
        System.out.println("实际日期：" + DateUtils.date2StringByDay(DateUtils.addDay(new Date(), 100)));
    }

    private void logDate(Date date) {
        System.out.println(DateUtils.date2StringByDay(date));
    }
}
