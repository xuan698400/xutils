package com.xuan.moho.design.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author xuan
 * @since 2023/3/7
 */
public class ProxyHandler implements InvocationHandler {

    private Object obj;

    public ProxyHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("大家注意，我是JDK动态代理，下面有人要发言了。");
        return method.invoke(obj, args);
    }

}
