package com.xuan.mix.bt.event;

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

import org.apache.commons.collections4.CollectionUtils;

/**
 * 发布者
 *
 * @author xuan
 * @since 2021/4/1
 */
public class EventPublisher {

    private final static AtomicInteger threadCounter = new AtomicInteger(1);

    /**
     * 异步通知默认线程池
     */
    private final static ExecutorService DEFAULT_WORK = new ThreadPoolExecutor(4, 4, 4,
        TimeUnit.MINUTES, new LinkedBlockingQueue<>(1000),
        (r) -> new Thread(r, "xUtils-EventPublisher-Thread" + threadCounter.get()), (r, executor) -> {
        //这里是拒绝策略，默认抛弃
    });

    private static final EventPublisher INSTANCE = new EventPublisher();

    private EventPublisher() {

    }

    public static EventPublisher instance() {
        return INSTANCE;
    }

    private List<EventSubscriber> subscribers = new ArrayList<>();

    private Map<Class, List<EventSubscriber>> subscriberMap = new ConcurrentHashMap<>();

    public void subscribe(EventSubscriber subscriber) {
        if (null == subscriber) {
            return;
        }

        subscribers.add(subscriber);

        Class<?>[] supportEventTypes = subscriber.supportEventTypes();
        if (null != supportEventTypes && supportEventTypes.length > 0) {
            for (Class<?> clazz : supportEventTypes) {
                List<EventSubscriber> subscriberList = subscriberMap.computeIfAbsent(clazz, k -> new ArrayList<>());
                subscriberList.add(subscriber);
                sortSubscriber(subscriberList);
            }
        }
    }

    public void reset() {
        subscribers.clear();
        subscriberMap.clear();
    }

    /**
     * 同步通知
     *
     * @param event 通知事件
     */
    public void syncFire(Event event) {
        doFire(event);
    }

    /**
     * 异步通知
     *
     * @param event 通知事件
     */
    public void asyncFire(Event event) {
        DEFAULT_WORK.submit(() -> doFire(event));
    }

    /**
     * 异步通知（自带异步线程池）
     *
     * @param event
     * @param executorService
     */
    public void asyncFire(Event event, ExecutorService executorService) {
        executorService.submit(() -> doFire(event));
    }

    private void doFire(Event event) {
        if (!this.hasSubscribers()) {
            return;
        }
        Class<?> eventType = event.getClass();
        List<EventSubscriber> subscribers = subscriberMap.get(eventType);
        if (CollectionUtils.isNotEmpty(subscribers)) {
            for (EventSubscriber subscriber : subscribers) {
                subscriber.fireEvent(event);
            }
        }
    }

    private void sortSubscriber(List<EventSubscriber> subscriberList) {
        subscriberList.sort(Comparator.comparingInt(EventSubscriber::priority));
    }

    private boolean hasSubscribers() {
        return null != subscribers && !subscribers.isEmpty();
    }

}

