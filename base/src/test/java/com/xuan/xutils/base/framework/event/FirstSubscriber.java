package com.xuan.xutils.base.framework.event;

/**
 * @author xuan
 * @since 2021/4/1
 */
public class FirstSubscriber implements EventSubscriber<MyEventBody> {

    @Override
    public void handle(EventModel<MyEventBody> eventModel) {

        System.out.println(String.format("执行线程[%s], 订阅者[FirstSubscriber], 消息name[%s]", Thread.currentThread().getName(),
            eventModel.getEventBody().getName()));
    }

    @Override
    public Class<MyEventBody> getEventBodyClass() {
        return MyEventBody.class;
    }

    @Override
    public int priority() {
        return NORMAL;
    }

}
