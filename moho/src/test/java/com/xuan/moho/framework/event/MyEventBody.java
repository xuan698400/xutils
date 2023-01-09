package com.xuan.moho.framework.event;

/**
 * 事件Body必须继承EventBody
 *
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
