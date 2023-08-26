package com.xuan.xutils.design.proxy;

/**
 * @author xuan
 * @since 2023/3/7
 */
public class Client {

    public static void main(String[] args) {
        ChildPerson childPerson = new ChildPerson();
        PersonProxy personProxy = new PersonProxy(childPerson);
        personProxy.sayHello();
        ManPerson manPerson = new ManPerson();
        personProxy = new PersonProxy(manPerson);
        personProxy.sayHello();
    }

}
