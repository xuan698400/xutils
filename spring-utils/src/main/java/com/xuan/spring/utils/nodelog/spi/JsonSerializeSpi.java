package com.xuan.spring.utils.nodelog.spi;

import com.alibaba.fastjson.JSON;

import com.xuan.mix.bt.nodelog.spi.SerializeSpi;

/**
 * @author xuan
 * @since 2021/9/4
 */
public class JsonSerializeSpi implements SerializeSpi {

    @Override
    public String toString(Object obj) {
        if (null == obj) {
            return "null";
        }
        return JSON.toJSONString(obj);
    }

}
