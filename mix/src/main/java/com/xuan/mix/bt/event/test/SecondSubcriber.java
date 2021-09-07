package com.xuan.mix.bt.event.test;

import com.xuan.mix.bt.event.EventModel;
import com.xuan.mix.bt.event.EventSubscriber;

/**
 * @author xuan
 * @since 2021/4/1
 */
public class SecondSubcriber implements EventSubscriber<MyEventBody> {

    @Override
    public void handle(EventModel<MyEventBody> eventModel) {
        System.out.println(eventModel.getEventBody().getName() + " by SecondSubcriber.");
    }

    @Override
    public Class<MyEventBody> getEventBodyClass() {
        return MyEventBody.class;
    }

    @Override
    public int priority() {
        return LOWEST;
    }

}
