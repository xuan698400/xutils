package com.xuan.crudboy.api.core.add;

import java.util.Map;

import com.xuan.crudboy.api.core.BaseCbRequest;

/**
 * @author xuan
 * @since 2023/11/10
 */
public class CbAddRequest extends BaseCbRequest {

    private Map<String, String> data;

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

}
