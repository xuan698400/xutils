package com.xuan.mix.bt.limiter;

import org.junit.Test;

/**
 * @author xuan
 * @since 2019/5/23
 */
public class RollingNumberTest {

    RollingNumber rollingNumber = new RollingNumber();

    @Test
    public void test() {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    testMock();
                }
            }).start();
        }

        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000);
                    System.out.println(rollingNumber.getAvg(CounterDataType.PASSED));
                }
            } catch (Exception e) {

            }
        }).start();

        try {
            System.in.read();
        } catch (Exception e) {
        }
        System.out.println("程序退出");
    }

    private void testMock() {
        try {
            rollingNumber.addOne(CounterDataType.PASSED);
            Thread.sleep(100);
        } catch (Exception e) {

        }
    }

}
