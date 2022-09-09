package com.xuan.distributed.tools.lock;

import com.xuan.distributed.tools.BaseTest;
import com.xuan.distributed.tools.lock.core.DbNonReentrantLock;
import com.xuan.distributed.tools.lock.core.DbReentrantLock;

/**
 * @author xuan
 * @since 2022/9/10
 */
public class Main extends BaseTest {

    private static int cnt;

    public static void main(String[] args) {
        testCount(getNonReentrantLock(), "getNonReentrantLock");
    }

    private static Lock getNonReentrantLock() {
        DbNonReentrantLock nonReentrantLock = new DbNonReentrantLock();
        nonReentrantLock.setDataSource(getDataSource());
        nonReentrantLock.init();
        return nonReentrantLock;
    }

    private static Lock getReentrantLock() {
        DbReentrantLock reentrantLock = new DbReentrantLock();
        reentrantLock.setDataSource(getDataSource());
        reentrantLock.init();
        return reentrantLock;
    }

    private static void testCount(Lock lock, String lockName) {
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
            });
        }
    }

    private static void test1(Lock lock, String lockName) {

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
