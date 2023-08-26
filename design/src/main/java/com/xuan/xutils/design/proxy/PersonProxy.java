package com.xuan.xutils.design.proxy;

/**
 * @author xuan
 * @since 2023/3/7
 */
public class PersonProxy implements Person {

    private Person person;

    public PersonProxy(Person person) {
        this.person = person;
    }

    @Override
    public void sayHello() {
        System.out.println("大家注意，我是静态代理，我要发言了。");
        person.sayHello();
    }

}
