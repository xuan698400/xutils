package com.xuan.moho.tools.lock;

import java.util.concurrent.CountDownLatch;

import com.xuan.moho.BaseDbTest;
import com.xuan.moho.tools.lock.db.DbNonReentrantLock;
import com.xuan.moho.tools.lock.db.DbReentrantLock;
import org.junit.Before;
import org.junit.Test;

/**
 * @author xuan
 * @since 2023/1/7
 */
public class LockTest extends BaseDbTest {

    private Lock reentrantLock;
    private Lock nonReentrantLock;
    private static int cnt;

    @Before
    public void initLock() {
        reentrantLock = DbReentrantLock.builder().dataSource(dataSource).build();
        nonReentrantLock = DbNonReentrantLock.builder().dataSource(dataSource).build();
    }

    @Test
    public void testReentrantLockCount() {
        testCount(reentrantLock, "testReentrantLockCount");
    }

    @Test
    public void testNonReentrantLockCount() {
        testCount(nonReentrantLock, "testNonReentrantLockCount");
    }

    @Test
    public void testReentrantLock() {
        test(reentrantLock, "testReentrantLock");
    }

    @Test
    public void testNonReentrantLock() {
        test(nonReentrantLock, "testNonReentrantLock");
    }

    private void testCount(Lock lock, String lockName) {
        CountDownLatch latch = new CountDownLatch(20);
        cnt = 0;
        for (int i = 0; i < 20; i++) {
            submit(() -> {
                for (int j = 0; j < 100; j++) {
                    boolean isLock = lock.tryLock(lockName, Integer.MAX_VALUE);
                    if (isLock) {
                        int temp = cnt;
                        sleep(10);
                        temp = temp + 1;
                        cnt = temp;
                        log(Thread.currentThread().getName() + ":" + cnt);
                        lock.unLock(lockName);
                    }
                }
                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            //Ingore
        }
    }

    private void test(Lock lock, String lockName) {

        //主线程第1次获取锁，预期成功
        boolean success = lock.tryLock(lockName, 10);
        if (success) {
            log("主线程，获取锁成功【预期】");
        } else {
            log("主线程，获取锁失败【非预期】");
        }

        //主线程第2次获取锁，可重入，预期成功
        success = lock.tryLock(lockName, 10);
        if (success) {
            log("主线程重入，获取锁成功【预期】");
        } else {
            log("主线程重入，获取锁失败【非预期】");
        }

        submit(() -> {
            boolean s = lock.tryLock(lockName, 10);
            if (s) {
                log("其他线程1，获取锁成功【非预期】");
            } else {
                log("其他线程1，获取锁失败【预期】");
            }
        });

        sleep(2000);

        //主线程把锁释放
        lock.unLock(lockName);

        submit(() -> {
            boolean s = lock.tryLock(lockName, 5);
            if (s) {
                log("其他线程2，获取锁成功【预期】");
            } else {
                log("其他线程2，获取锁失败【非预期】");
            }
            lock.unLock(lockName);
        });

        sleep(2000);

        // 主线程再来，可以获取锁
        success = lock.tryLock(lockName, 5);
        if (success) {
            log("主线程再来，获取锁成功【预期】");
        } else {
            log("主线程再来，获取锁失败【非预期】");
        }

        //等锁超时
        sleep(2000);
        sleep(2000);
        sleep(2000);

        submit(() -> {
            boolean s = lock.tryLock(lockName, 10);
            if (s) {
                log("其他线程3，获取锁成功【预期】");
            } else {
                log("其他线程3，获取锁失败【非预期】");
            }
            lock.unLock(lockName);
        });

        sleep(2000);
    }

}
