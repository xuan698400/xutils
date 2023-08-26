package com.xuan.xutils.design.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author xuan
 * @since 2023/3/7
 */
public class CglibInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("大家注意，我是CGLIB动态代理，下面有人要发言了。");
        return methodProxy.invokeSuper(obj, args);
    }

}
