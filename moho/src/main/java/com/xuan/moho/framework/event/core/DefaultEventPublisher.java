package com.xuan.moho.framework.event.core;

import java.util.concurrent.ExecutorService;

import com.xuan.moho.framework.event.EventBus;
import com.xuan.moho.framework.event.EventModel;
import com.xuan.moho.framework.event.EventPublisher;

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
        eventBus.asyncFire(eventModel);
    }

    @Override
    public void asyncPublish(EventModel eventModel, ExecutorService executorService) {
        eventBus.asyncFire(eventModel, executorService);
    }

}
