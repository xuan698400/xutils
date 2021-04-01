package com.xuan.mix.bt.event;

/**
 * 订阅者
 *
 * @author xuan
 * @since 2021/4/1
 */
public interface EventSubscriber {

    int HIGHEST = Integer.MIN_VALUE;
    int NORMAL = 0;
    int LOWEST = Integer.MAX_VALUE;

    /**
     * 返回订阅者优先级，越小优先级越高，默认是0
     *
     * @return 优先级
     */
    default int priority() {
        return NORMAL;
    }

    /**
     * 执行事件
     *
     * @param event 事件对象
     */
    void fireEvent(Object event);

    /**
     * 所支持事件类型
     *
     * @return 支持的事件类型
     */
    Class[] supportEventTypes();
}
