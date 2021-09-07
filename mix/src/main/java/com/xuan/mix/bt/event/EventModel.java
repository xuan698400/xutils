package com.xuan.mix.bt.event;

import java.util.Map;

/**
 * 事件模型
 *
 * @author xuan
 * @since 2021/9/6
 */
public class EventModel<EventBody> {

    public EventModel(EventBody eventBody) {
        this.eventBody = eventBody;
    }

    /**
     * 扩展属性MAP，方便扩展
     */
    private Map<String, String> extAttrMap;
    /**
     * 事件内容
     */
    private EventBody eventBody;

    public EventBody getEventBody() {
        return eventBody;
    }

    public void setEventBody(EventBody eventBody) {
        this.eventBody = eventBody;
    }

    public Map<String, String> getExtAttrMap() {
        return extAttrMap;
    }

    public void setExtAttrMap(Map<String, String> extAttrMap) {
        this.extAttrMap = extAttrMap;
    }

}
