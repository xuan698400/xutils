package com.xuan.mix.bt.event.test;

import com.xuan.mix.bt.event.EventSubscriber;

/**
 * @author xuan
 * @since 2021/4/1
 */
public class SecondSubcriber implements EventSubscriber {

    @Override
    public void fireEvent(Object event) {
        if (event instanceof MyEvent) {
            MyEvent myEvent = (MyEvent)event;
            System.out.println(myEvent.getName() + " by SecondSubcriber.");
        }
    }

    @Override
    public Class[] supportEventTypes() {
        return new Class[] {MyEvent.class};
    }

    @Override
    public int priority() {
        return LOWEST;
    }

}
