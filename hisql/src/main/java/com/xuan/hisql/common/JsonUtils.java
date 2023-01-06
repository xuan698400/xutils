package com.xuan.hisql.common;

import com.alibaba.fastjson.JSON;

import com.xuan.mix.an.common.utils.StringUtils;

/**
 * @author xuan
 * @since 2022/8/25
 */
public class JsonUtils {

    public static <T> T toObject(String text, Class<T> clazz) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }

        try {
            return JSON.parseObject(text, clazz);
        } catch (Exception e) {
            //Ignore
        }
        return null;
    }

    public static String toString(Object obj) {
        if (null == obj) {
            return null;
        }
        try {
            return JSON.toJSONString(obj);
        } catch (Exception e) {
            //Ignore
        }
        return null;
    }

}
