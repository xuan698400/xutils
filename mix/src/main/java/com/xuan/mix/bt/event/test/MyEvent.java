package com.xuan.mix.bt.event.test;

import com.xuan.mix.bt.event.Event;

/**
 * @author xuan
 * @since 2021/4/1
 */
public class MyEvent implements Event {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
