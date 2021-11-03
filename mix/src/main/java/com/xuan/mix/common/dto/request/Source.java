package com.xuan.mix.common.dto.request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuan
 * @since 2021/11/3
 */
public class Source implements Serializable {
    private static final long serialVersionUID = 1L;

    private String from;

    private Map<String, String> sourceExtMap = new HashMap();

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Map<String, String> getSourceExtMap() {
        return sourceExtMap;
    }

    public void setSourceExtMap(Map<String, String> sourceExtMap) {
        this.sourceExtMap = sourceExtMap;
    }

}
