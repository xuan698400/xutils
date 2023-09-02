package com.xuan.xutils.design.observer;

import java.util.Observable;

/**
 * 继承了Observable，表示该对象可被观察，即可注册观察者，当该对象数据发生变更时，可进行通知处理
 *
 * @author xuan
 * @since 2022/11/18
 */
public class ObservableData extends Observable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void fire() {
        setChanged();
        notifyObservers(name);
    }

}
