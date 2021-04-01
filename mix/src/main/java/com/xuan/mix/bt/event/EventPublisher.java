package com.xuan.mix.bt.event;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections4.CollectionUtils;

/**
 * @author xuan
 * @since 2021/4/1
 */
public class EventPublisher {
    private static final EventPublisher INSTANCE = new EventPublisher();

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
                System.out.println(subscriberList);
            }
        }
    }

    public void reset() {
        subscribers.clear();
        subscriberMap.clear();
    }

    public void fire(Object event) {
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

