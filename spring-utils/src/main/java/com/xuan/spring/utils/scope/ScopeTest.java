package com.xuan.spring.utils.scope;

import com.xuan.spring.utils.App;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.SimpleThreadScope;

/**
 * @author xuan
 * @since 2021/9/24
 */
public class ScopeTest {

    public static void test() {
        ConfigurableListableBeanFactory beanFactory = ((GenericApplicationContext)App.getApplicationContext())
            .getBeanFactory();

        SimpleThreadScope simpleThreadScope = (SimpleThreadScope)beanFactory.getRegisteredScope(
            "thread");

        new Thread(new Runnable() {
            @Override
            public void run() {
                ScopeBean scopeBean = beanFactory.getBean(ScopeBean.class);
                scopeBean.setName("xxx");
                System.out.println("thread1-" + scopeBean.getName());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ScopeBean scopeBean = beanFactory.getBean(ScopeBean.class);
                System.out.println("thread2-" + scopeBean.getName());
                scopeBean.setName("abc");

                ScopeBean scopeBean2 = beanFactory.getBean(ScopeBean.class);
                System.out.println("thread2-" + scopeBean2.getName());
            }
        }).start();
    }
}
