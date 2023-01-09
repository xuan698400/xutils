package com.xuan.moho.framework.event;

import java.util.concurrent.ExecutorService;

/**
 * @author xuan
 * @since 2021/9/7
 */
public interface EventPublisher {

    void publish(EventModel eventModel);

    void asyncPublish(EventModel eventModel);

    void asyncPublish(EventModel eventModel, ExecutorService executorService);
}
