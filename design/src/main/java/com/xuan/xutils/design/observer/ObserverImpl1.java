package com.xuan.xutils.design.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者1
 *
 * @author xuan
 * @since 2022/11/18
 */
public class ObserverImpl1 implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("ObserverImpl1_" + arg);
    }
}
