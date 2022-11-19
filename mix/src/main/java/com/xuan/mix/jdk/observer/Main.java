package com.xuan.mix.jdk.observer;

/**
 * @author xuan
 * @since 2022/11/18
 */
public class Main {

    public static void main(String[] args) {
        ObservableData data = new ObservableData();
        data.addObserver(new ObserverImpl1());
        data.addObserver(new ObserverImpl2());
        data.setName("123");
    }

}
