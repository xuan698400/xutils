package com.xuan.mix.bt.event;

/**
 * @author xuan
 * @since 2021/4/1
 */
public interface EventSubscriber<Body extends EventBody> {

    int HIGHEST = Integer.MAX_VALUE;
    int NORMAL = 0;
    int LOWEST = Integer.MIN_VALUE;

    /**
     * 返回订阅者优先级，越大优先级越高，默认是0
     *
     * @return 优先级
     */
    default int priority() {
        return NORMAL;
    }

    /**
     * 处理事件
     *
     * @param eventModel 事件模型
     */
    void handle(EventModel<Body> eventModel);

    /**
     * 所支持事件类型
     *
     * @return 支持的事件类型
     */
    Class<Body> getEventBodyClass();
}
