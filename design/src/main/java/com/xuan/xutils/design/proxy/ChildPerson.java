package com.xuan.xutils.design.proxy;

/**
 * @author xuan
 * @since 2023/3/7
 */
public class ChildPerson implements Person {

    @Override
    public void sayHello() {
        System.out.println("你好，我是小孩子。");
    }
}
