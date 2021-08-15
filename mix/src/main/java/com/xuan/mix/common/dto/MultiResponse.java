package com.xuan.mix.common.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author xuan
 * @since 2021/7/12
 */
public class MultiResponse<T> extends Response {

    private static final long serialVersionUID = 1L;

    private Collection<T> data;

    public List<T> getData() {
        return null == data ? Collections.emptyList() : new ArrayList<>(data);
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

    public boolean isEmpty() {
        return data == null || data.isEmpty();
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public static MultiResponse buildSuccess() {
        MultiResponse response = new MultiResponse();
        response.setSuccess(true);
        return response;
    }

    public static <T> MultiResponse<T> buildSuccess(Collection<T> data) {
        MultiResponse<T> response = new MultiResponse<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static MultiResponse buildFailure(String errCode, String errMessage) {
        MultiResponse response = new MultiResponse();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }

}
