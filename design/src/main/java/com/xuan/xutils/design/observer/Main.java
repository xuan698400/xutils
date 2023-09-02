package com.xuan.xutils.design.observer;

/**
 * JDK自带的观察者模式
 *
 * @author xuan
 * @since 2022/11/18
 */
public class Main {

    public static void main(String[] args) {
        ObservableData data = new ObservableData();
        //注册2个观察者到观察对象上
        data.addObserver(new ObserverImpl1());
        data.addObserver(new ObserverImpl2());
        data.setName("123");
        //设置状态已变更，并进行通知
        data.fire();
    }

}
