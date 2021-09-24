package com.xuan.spring.utils.scope;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.stereotype.Component;

/**
 * 注册一个线程隔离作用域的Scope
 *
 * @author xuan
 * @since 2021/9/24
 */
@Component
public class ScopeListener implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory)
        throws BeansException {

        configurableListableBeanFactory.registerScope("thread", new SimpleThreadScope());
    }

}
