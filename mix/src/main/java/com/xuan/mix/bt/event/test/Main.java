package com.xuan.mix.bt.event.test;

import com.xuan.mix.bt.event.EventPublisher;

/**
 * @author xuan
 * @since 2021/4/1
 */
public class Main {

    public static void main(String[] args) {
        EventPublisher.instance().subscribe(new SecondSubcriber());
        EventPublisher.instance().subscribe(new FirstSubscriber());

        ////
        MyEvent myEvent = new MyEvent();
        myEvent.setName("你好");
        EventPublisher.instance().asyncFire(myEvent);
    }

}
