package com.xuan.xutils.design.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者2
 *
 * @author xuan
 * @since 2022/11/18
 */
public class ObserverImpl2 implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("ObserverImpl2_" + arg);
    }
}
