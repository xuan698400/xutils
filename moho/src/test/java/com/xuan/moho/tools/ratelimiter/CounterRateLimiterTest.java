package com.xuan.moho.tools.ratelimiter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

import com.xuan.moho.BaseTest;
import com.xuan.moho.tools.ratelimiter.counter.CounterRateLimiter;
import com.xuan.moho.tools.ratelimiter.counter.CounterRateLimiter.Monitor;
import org.junit.Before;
import org.junit.Test;

/**
 * @author xuan
 * @since 2023/1/13
 */
public class CounterRateLimiterTest extends BaseTest {

    private final static long INTERVAL = 1000L;

    private RateLimiter rateLimiter;

    private List<AtomicLong> passList = new CopyOnWriteArrayList<>();

    private List<AtomicLong> blockList = new CopyOnWriteArrayList<>();

    @Before
    public void init() {
        initList();
        rateLimiter = CounterRateLimiter.builder().interval(INTERVAL).limitQps(10).monitor(new Monitor() {
            @Override
            public void acquire(long start, long now, boolean isAcquired) {
                int index = (int)((now - start) / 100);
                if (isAcquired) {
                    passList.get(index).incrementAndGet();
                } else {
                    blockList.get(index).incrementAndGet();
                }
            }

            @Override
            public void reset(long newStart) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 10; i++) {
                    sb.append(String.format("%s[%s/%s],", i, passList.get(i).get(), blockList.get(i).get()));
                }
                log(String.format("start[%s]:%s", newStart, sb.toString()));
                initList();
            }
        }).build();
    }

    private void initList() {
        passList = new CopyOnWriteArrayList<>();
        blockList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            passList.add(new AtomicLong(0));
            blockList.add(new AtomicLong(0));
        }
    }

    @Test
    public void testTryAcquire() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100000; j++) {
                        rateLimiter.tryAcquire();
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
