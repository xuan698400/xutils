package com.xuan.moho.design.proxy.cglib;

import com.xuan.moho.design.proxy.ManPerson;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author xuan
 * @since 2023/3/7
 */
public class Client {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ManPerson.class);
        enhancer.setCallback(new CglibInterceptor());
        ManPerson manPerson = (ManPerson)enhancer.create();
        manPerson.sayHello();
    }

}
