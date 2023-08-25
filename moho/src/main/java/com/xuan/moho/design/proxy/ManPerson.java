package com.xuan.moho.design.proxy;

/**
 * @author xuan
 * @since 2023/3/7
 */
public class ManPerson implements Person {

    @Override
    public void sayHello() {
        System.out.println("你好，我是男人。");
    }
}
