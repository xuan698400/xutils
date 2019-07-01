package com.xuan.mix.bt.limiter;

import org.junit.Test;

/**
 * @author xuan
 * @since 2019/5/23
 */
public class LimiterTest {

    RateLimiter rateLimiter = new RateLimiter(100);

    @Test
    public void test() {

        for (int i = 0; i < 20; i++) {
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
                    System.out.println(String
                        .format("qps:%s, passed:%s, blocked:%s", rateLimiter.getQps(), rateLimiter.getPassed(),
                            rateLimiter.getBlocked()));
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
            rateLimiter.tryPass();
            Thread.sleep(100);
        } catch (Exception e) {

        }
    }

}
