package com.xuan.mix.bt.event.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.xuan.mix.bt.event.EventBus;
import com.xuan.mix.bt.event.EventModel;
import com.xuan.mix.bt.event.EventSubscriber;

/**
 * @author xuan
 * @since 2021/9/7
 */
public class DefaultEventBus implements EventBus {

    private final static AtomicInteger THREAD_COUNTER = new AtomicInteger(1);

    /**
     * 异步通知默认线程池
     */
    private final static ExecutorService DEFAULT_WORK = new ThreadPoolExecutor(4, 4, 4,
        TimeUnit.MINUTES, new LinkedBlockingQueue<>(1000),
        (r) -> new Thread(r, "xUtils-DefaultEventBus-Thread" + THREAD_COUNTER.get()), (r, executor) -> {
        //这里是拒绝策略，默认抛弃
    });

    private List<EventSubscriber> subscribers = new ArrayList<>();

    private Map<Class, List<EventSubscriber>> subscriberMap = new ConcurrentHashMap<>();

    @Override
    public void addSubscriber(EventSubscriber subscriber) {
        if (null == subscriber) {
            return;
        }

        subscribers.add(subscriber);

        Class<?> eventBodyClass = subscriber.getEventBodyClass();
        if (null != eventBodyClass) {
            List<EventSubscriber> subscriberList = subscriberMap.computeIfAbsent(eventBodyClass,
                k -> new ArrayList<>());
            subscriberList.add(subscriber);
            sortSubscriber(subscriberList);
        }
    }

    @Override
    public void reset() {
        subscribers.clear();
        subscriberMap.clear();
    }

    @Override
    public void syncFire(EventModel eventModel) {
        doFire(eventModel);
    }

    @Override
    public void asyncFire(EventModel eventModel) {
        DEFAULT_WORK.submit(() -> doFire(eventModel));
    }

    @Override
    public void asyncFire(EventModel eventModel, ExecutorService executorService) {
        executorService.submit(() -> doFire(eventModel));
    }

    private void sortSubscriber(List<EventSubscriber> subscriberList) {
        subscriberList.sort(Comparator.comparingInt(EventSubscriber::priority));
    }

    private void doFire(EventModel eventModel) {
        if (!this.hasSubscribers()) {
            return;
        }
        Class<?> eventBodyClass = eventModel.getEventBody().getClass();
        List<EventSubscriber> subscribers = subscriberMap.get(eventBodyClass);
        if (null != subscribers) {
            for (EventSubscriber subscriber : subscribers) {
                subscriber.handle(eventModel);
            }
        }
    }

    private boolean hasSubscribers() {
        return null != subscribers && !subscribers.isEmpty();
    }

}
