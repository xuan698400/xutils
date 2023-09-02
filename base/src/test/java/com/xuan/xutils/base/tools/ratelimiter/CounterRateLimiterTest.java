package com.xuan.xutils.base.tools.ratelimiter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.xuan.xutils.base.BaseTest;
import com.xuan.xutils.base.tools.ratelimiter.counter.CounterRateLimiter;
import org.junit.Before;
import org.junit.Test;

/**
 * @author xuan
 * @since 2023/1/13
 */
public class CounterRateLimiterTest extends BaseTest {

    private ScheduledThreadPoolExecutor scheduled100 = new ScheduledThreadPoolExecutor(1);

    private AtomicLong passCount = new AtomicLong(0L);
    private AtomicLong blockCount = new AtomicLong(0L);
    private List<AtomicLong> passList = new CopyOnWriteArrayList<>();
    private List<AtomicLong> blockList = new CopyOnWriteArrayList<>();
    private AtomicLong bucket = new AtomicLong(0L);

    private RateLimiter rateLimiter;

    @Before
    public void init() {
        scheduled100.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                passList.add(new AtomicLong(passCount.get()));
                blockList.add(new AtomicLong(blockCount.get()));
                bucket.incrementAndGet();
                passCount.set(0L);
                blockCount.set(0L);

                if (bucket.get() == 10) {
                    //记录
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0, n = passList.size(); i < n; i++) {
                        sb.append(
                            String.format("%s[%s/%s],", i, passList.get(i).get(), blockList.get(i).get()));
                    }
                    log(String.format("start[%s]:%s", System.currentTimeMillis(), sb.toString()));
                    //
                    passList = new CopyOnWriteArrayList<>();
                    blockList = new CopyOnWriteArrayList<>();
                    bucket.set(0L);
                }
            }
        }, 0L, 100L, TimeUnit.MILLISECONDS);

        //每秒通过10个请求
        rateLimiter = CounterRateLimiter.builder().interval(1000L).limitQps(10).build();
    }

    @Test
    public void testTryAcquire() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100000; j++) {
                        boolean isAcquired = rateLimiter.tryPass();
                        if (isAcquired) {
                            passCount.incrementAndGet();
                        } else {
                            blockCount.incrementAndGet();
                        }
                        sleep(10);
                    }
                    latch.countDown();
                }
            });
        }

        latch.await();
        log("testTryAcquire finish");
    }

}
