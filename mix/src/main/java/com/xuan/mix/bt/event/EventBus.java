package com.xuan.mix.bt.event;

import java.util.concurrent.ExecutorService;

/**
 * @author xuan
 * @since 2021/9/7
 */
public interface EventBus {

    /**
     * 新增订阅者
     *
     * @param subscriber 订阅者实例
     */
    void addSubscriber(EventSubscriber subscriber);

    /**
     * 重置，清空所有订阅者
     */
    void reset();

    /**
     * 同步通知
     *
     * @param eventModel 事件模型
     */
    void syncFire(EventModel eventModel);

    /**
     * 异步通知
     *
     * @param eventModel 事件模型
     */
    void asyncFire(EventModel eventModel);

    /**
     * 异步通知（自带异步线程池）
     *
     * @param eventModel      事件模型
     * @param executorService 异步执行的线程池
     */
    void asyncFire(EventModel eventModel, ExecutorService executorService);
}
