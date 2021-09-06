package com.xuan.mix.bt.nodelog.spi.impl;

import java.util.Arrays;

import com.xuan.mix.bt.nodelog.spi.SerializeSpi;

/**
 * 默认使用Object.toString序列化
 *
 * @author xuan
 * @since 2021/9/3
 */
public class DefaultSerializeSpi implements SerializeSpi {

    @Override
    public String toString(Object obj) {
        if (null == obj) {
            return "null";
        }

        if (obj.getClass().isArray()) {
            return Arrays.toString((Object[])obj);
        }
        return obj.toString();
    }

}
