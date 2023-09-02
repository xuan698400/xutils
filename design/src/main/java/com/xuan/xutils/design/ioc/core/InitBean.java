package com.xuan.xutils.design.ioc.core;

/**
 * 实现该接口，IOC在实例化Bean之后，会调用该方法进行Bean的初始化
 *
 * @author xuan
 * @since 2023/1/4
 */
public interface InitBean {

    /**
     * Bean初始化
     *
     * @param context 容器上下文
     */
    void init(ApplicationContext context);
}
