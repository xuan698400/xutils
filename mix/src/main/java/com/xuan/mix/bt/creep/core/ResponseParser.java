package com.xuan.mix.bt.creep.core;

/**
 * @author xuan
 * @since 2020/11/1
 */
public interface ResponseParser<T> {

    T parser(Response response);
}
