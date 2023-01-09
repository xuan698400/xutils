package com.xuan.moho.framework.event;

import java.util.Map;

/**
 * @author xuan
 * @since 2021/9/6
 */
public class EventModel<EventBody> {

    public EventModel(EventBody eventBody) {
        this.eventBody = eventBody;
    }

    private Map<String, String> extAttrMap;

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
