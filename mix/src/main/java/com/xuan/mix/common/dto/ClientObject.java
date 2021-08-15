package com.xuan.mix.common.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuan
 * @since 2021/7/12
 */
public class ClientObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, String> extMap = new HashMap<String, String>();

    public String getExt(String key) {
        if (null != extMap) {
            return extMap.get(key);
        }
        return null;
    }

    public void putExt(String key, String value) {
        this.extMap.put(key, value);
    }

    public Map<String, String> getExtMap() {
        return extMap;
    }

    public void setExtMap(Map<String, String> extMap) {
        this.extMap = extMap;
    }

}
