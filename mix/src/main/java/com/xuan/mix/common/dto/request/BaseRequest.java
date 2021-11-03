package com.xuan.mix.common.dto.request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuan
 * @since 2021/11/3
 */
public class BaseRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Source source;

    private Map<String, String> bizExtMap = new HashMap();

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Map<String, String> getBizExtMap() {
        return bizExtMap;
    }

    public void setBizExtMap(Map<String, String> bizExtMap) {
        this.bizExtMap = bizExtMap;
    }

}
