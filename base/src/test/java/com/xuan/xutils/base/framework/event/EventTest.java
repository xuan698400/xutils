package com.xuan.xutils.base.framework.event;

import com.xuan.xutils.base.framework.event.core.DefaultEventBus;
import com.xuan.xutils.base.framework.event.core.DefaultEventPublisher;
import org.junit.Before;
import org.junit.Test;

/**
 * @author xuan
 * @since 2023/1/9
 */
public class EventTest {

    private EventPublisher eventPublisher;

    @Before
    public void init() {
        //事件总线
        EventBus eventBus = new DefaultEventBus();
        //把订阅者注册到事件总线
        eventBus.addSubscriber(new FirstSubscriber());
        eventBus.addSubscriber(new SecondSubcriber());
        //事件发布者
        eventPublisher = new DefaultEventPublisher(eventBus);
    }

    @Test
    public void testPublish() {
        //构建事件模型
        MyEventBody myEventBody = new MyEventBody();
        myEventBody.setName("xuan");
        EventModel<MyEventBody> eventModel = new EventModel<>(myEventBody);
        //发布操作
        eventPublisher.publish(eventModel);
    }

    @Test
    public void testAsyncPublish() {
        //构建事件模型
        MyEventBody myEventBody = new MyEventBody();
        myEventBody.setName("xuan");
        EventModel<MyEventBody> eventModel = new EventModel<>(myEventBody);
        //发布操作
        eventPublisher.asyncPublish(eventModel);
    }

}
