package com.xuan.mix.bt.event.test;

import com.xuan.mix.bt.event.EventBus;
import com.xuan.mix.bt.event.EventModel;
import com.xuan.mix.bt.event.EventPublisher;
import com.xuan.mix.bt.event.core.DefaultEventBus;
import com.xuan.mix.bt.event.core.DefaultEventPublisher;

/**
 * @author xuan
 * @since 2021/4/1
 */
public class Main {

    public static void main(String[] args) throws Exception {
        //构建事件总线
        EventBus eventBus = new DefaultEventBus();

        //构建订阅者 & 订阅到事件总线
        FirstSubscriber firstSubscriber = new FirstSubscriber();
        SecondSubcriber secondSubcriber = new SecondSubcriber();
        eventBus.addSubscriber(firstSubscriber);
        eventBus.addSubscriber(secondSubcriber);

        //构建发布者
        EventPublisher eventPublisher = new DefaultEventPublisher(eventBus);

        //构建事件模型
        MyEventBody myEventBody = new MyEventBody();
        myEventBody.setName("xuan");
        EventModel<MyEventBody> eventModel = new EventModel<>(myEventBody);

        //发布操作
        eventPublisher.publish(eventModel);
    }

}
