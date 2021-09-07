package com.xuan.mix.bt.event.core;

import java.util.concurrent.ExecutorService;

import com.xuan.mix.bt.event.EventBus;
import com.xuan.mix.bt.event.EventModel;
import com.xuan.mix.bt.event.EventPublisher;

/**
 * @author xuan
 * @since 2021/9/7
 */
public class DefaultEventPublisher implements EventPublisher {

    public EventBus eventBus;

    public DefaultEventPublisher(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void publish(EventModel eventModel) {
        eventBus.syncFire(eventModel);
    }

    @Override
    public void asyncPublish(EventModel eventModel) {
        eventBus.syncFire(eventModel);
    }

    @Override
    public void asyncPublish(EventModel eventModel, ExecutorService executorService) {
        eventBus.asyncFire(eventModel, executorService);
    }

}
