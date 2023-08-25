package com.xuan.moho.design.proxy.jdk;

import java.lang.reflect.Proxy;

import com.xuan.moho.design.proxy.ChildPerson;
import com.xuan.moho.design.proxy.ManPerson;
import com.xuan.moho.design.proxy.Person;
import com.xuan.moho.design.proxy.PersonProxy;

/**
 * @author xuan
 * @since 2023/3/7
 */
public class Client {

    public static void main(String[] args) {

        //静态代理
        ChildPerson childPerson = new ChildPerson();
        PersonProxy personProxy = new PersonProxy(childPerson);
        personProxy.sayHello();
        ManPerson manPerson = new ManPerson();
        personProxy = new PersonProxy(manPerson);
        personProxy.sayHello();

        //动态代理
        Person person = (Person)Proxy.newProxyInstance(Client.class.getClassLoader(), new Class[] {Person.class},
            new ProxyHandler(childPerson));
        person.sayHello();

        person = (Person)Proxy.newProxyInstance(Client.class.getClassLoader(), new Class[] {Person.class},
            new ProxyHandler(manPerson));
        person.sayHello();
    }

}
