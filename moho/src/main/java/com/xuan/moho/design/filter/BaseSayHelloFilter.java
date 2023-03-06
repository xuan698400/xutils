package com.xuan.moho.design.filter;

import java.util.Objects;

/**
 * @author xuan
 * @since 2023/3/6
 */
public abstract class BaseSayHelloFilter {

    private BaseSayHelloFilter next;

    public final Response handle(Request request) {
        if (Objects.equals(supportLang(), request.getLang())) {
            return doHandle(request);
        }

        if (null != next) {
            return next.handle(request);
        } else {
            throw new RuntimeException("Not found filter.");
        }
    }

    protected abstract Response doHandle(Request request);

    protected abstract String supportLang();

    public void setNext(BaseSayHelloFilter next) {
        this.next = next;
    }

}
