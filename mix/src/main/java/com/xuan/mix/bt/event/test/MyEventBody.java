package com.xuan.mix.bt.event.test;

import com.xuan.mix.bt.event.EventBody;

/**
 * @author xuan
 * @since 2021/9/7
 */
public class MyEventBody implements EventBody {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
