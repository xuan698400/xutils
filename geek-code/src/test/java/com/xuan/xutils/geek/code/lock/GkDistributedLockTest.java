package com.xuan.xutils.geek.code.lock;

import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author xuan
 * @since 2023/9/10
 */
public class GkDistributedLockTest {

    private final static Object OBJECT = new Object();

    public static void main(String[] args) throws SQLException {
        //1、构建数据源，一般系统已经存在的话，就可以复用
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/bpmweb?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("bpmweb");
        dataSource.setPassword("123456");

        //2、构建一个可重入的锁，并初始化
        GkDistributedLock lock = new GkDistributedLock(dataSource,
            "GkDistributedLockTest");
        lock.init();

        //3、获取锁/释放锁多线程测试使用

        //先强制释放锁，该操作不会判断锁是否是当前线程所持有，直接暴力释放，谨慎使用
        lock.unLock(false);

        // 主线程第1次获取锁，预期成功
        boolean success = lock.tryLock(Integer.MAX_VALUE, true);
        if (success) {
            log("主线程获取锁，成功【预期】");
        } else {
            log("主线程获取锁，失败【非预期】");
        }

        //主线程第2次获取锁，可重入，预期成功
        success = lock.tryLock(Integer.MAX_VALUE, true);
        if (success) {
            log("主线程重入获取锁，成功【预期】");
        } else {
            log("主线程重入获取锁，失败【非预期】");
        }

        //其他线程，尝试获取，因为可重入，所以会对持有者进行识别，这里是无法获取到的
        new Thread(() -> {
            try {
                boolean s = lock.tryLock(5, true);
                if (s) {
                    log("其他线程1，尝试获取被主线程锁定的锁，成功【非预期】");
                } else {
                    log("其他线程1，尝试获取被主线程锁定的锁，失败【预期】");
                }
            } catch (Exception e) {
                //Ignore
            }
            doNotify();
        }).start();

        doWait();

        //主线程把锁释放
        success = lock.unLock(false);
        if (success) {
            log("主线程释放锁，成功【预期】");
        } else {
            log("主线程释放锁，失败【非预期】");
        }

        //主线程释放锁之后，再用其他线程，尝试获取，这时锁是空闲状态，应该可以获取到
        new Thread(() -> {
            try {
                boolean s = lock.tryLock(10, true);
                if (s) {
                    log("其他线程2，在主线程释放锁后获取锁，成功【预期】");
                } else {
                    log("其他线程2，在主线程释放锁后获取锁，失败【非预期】");
                }
                doNotify();
            } catch (Exception e) {
                //Ignore
            }
        }).start();

        doWait();

        // 主线程，再来获取锁，因为已经被其他线程获取了，这里是获取不到的
        success = lock.tryLock(10, true);
        if (success) {
            log("在其他线程持有锁，主线程获取锁，成功【非预期】");
        } else {
            log("在其他线程持有锁，主线程获取锁，失败【预期】");
        }

        //上面其他线程，锁超时是10秒，所以理论上我等10秒后，锁又可以被获取了
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            //Ignore
        }

        // 主线程，再来获取锁，应该可以获取到了
        success = lock.tryLock(10, true);
        if (success) {
            log("等其他线程锁超时后，主线程获取锁，成功【预期】");
        } else {
            log("等其他线程锁超时后，主线程获取锁，失败【非预期】");
        }

        //测试不可重入方式获取锁
        success = lock.tryLock(10, false);
        if (success) {
            log("测试不可重入方式获取锁，主线程获取锁，成功【非预期】");
        } else {
            log("测试不可重入方式获取锁，主线程获取锁，失败【预期】");
        }

        //最后其他线程强制释放锁
        new Thread(() -> {
            try {
                boolean s = lock.unLock(false);
                if (s) {
                    log("其他线程3，使用强制释放锁，成功【预期】");
                } else {
                    log("其他线程3，使用强制释放锁，失败【非预期】");
                }
            } catch (Exception e) {
                //Ignore
            }
            doNotify();
        }).start();

        doWait();
        log("测试结束");
    }

    private static void log(String msg) {
        System.out.println(String.format("线程[%s]：%s", Thread.currentThread().getName(), msg));
    }

    private static void doWait() {
        synchronized (OBJECT) {
            try {
                OBJECT.wait();
            } catch (InterruptedException e) {
                //Ingore
            }
        }
    }

    private static void doNotify() {
        synchronized (OBJECT) {
            OBJECT.notifyAll();
        }
    }

}
