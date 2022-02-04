package com.xuan.lock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;

import com.xuan.lock.db.DbHelper;
import com.xuan.lock.db.DbLock;
import com.xuan.lock.db.DbReentrantLock;

/**
 * @author xuan
 * @since 2022/1/27
 */
public class Test {

    private static final DbLock lock = new DbReentrantLock();

    private static final DruidDataSource dataSource;

    static {
        dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/bpmweb?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("bpmweb");
        dataSource.setPassword("123456");
        lock.init(dataSource);
    }

    public static void main(String[] args) {
        test();
    }

    private static void insertTest() {
        DbHelper.insertLock(dataSource, "testResource", "value", 1000);
        DbHelper.insertLock(dataSource, "testResource", "value", 1000);
    }

    private static void test() {
        //强制清理锁
        lock.forceUnLock("testResource");

        //主线程第1次获取锁，预期成功
        boolean success = lock.tryLock("testResource", 10 * 1000);
        if (success) {
            System.out.println("主线程，获取锁成功【预期】");
        } else {
            System.out.println("主线程，获取锁失败【非预期】");
        }

        //主线程第2次获取锁，可重入，预期成功
        success = lock.tryLock("testResource", 10 * 1000);
        if (success) {
            System.out.println("主线程重入，获取锁成功【预期】");
        } else {
            System.out.println("主线程重入，获取锁失败【非预期】");
        }

        //其他线程1，因为主线程锁住了，所以预期失败
        new Thread(() -> {
            boolean s = lock.tryLock("testResource", 10 * 1000);
            if (s) {
                System.out.println("其他线程1，获取锁成功【非预期】");
            } else {
                System.out.println("其他线程1，获取锁失败【预期】");
            }
        }).start();

        //主线程把锁释放
        lock.unLock("testResource");

        //其他线程2，自然可以获取锁，锁完后马上释放
        new Thread(() -> {
            boolean s = lock.tryLock("testResource", 5 * 1000);
            if (s) {
                System.out.println("其他线程2，获取锁成功【预期】");
            } else {
                System.out.println("其他线程2，获取锁失败【非预期】");
            }
            lock.unLock("testResource");
        }).start();

        sleep();

        // 主线程再来，可以获取锁
        success = lock.tryLock("testResource", 5 * 1000);
        if (success) {
            System.out.println("主线程再来，获取锁成功【预期】");
        } else {
            System.out.println("主线程再来，获取锁失败【非预期】");
        }

        //等锁超时
        sleep();
        sleep();
        sleep();

        //其他线程3获取锁，可以抢到，因为主线程那个锁超时了
        new Thread(() -> {
            boolean s = lock.tryLock("testResource", 10 * 1000);
            if (s) {
                System.out.println("其他线程3，获取锁成功【预期】");
            } else {
                System.out.println("其他线程3，获取锁失败【非预期】");
            }
            lock.unLock("testResource");
        }).start();
        sleep();
    }

    private static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            //
        }
    }

}
