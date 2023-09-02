package com.xuan.xutils.design.ioc.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean工厂
 *
 * @author xuan
 * @since 2022/12/8
 */
public class BeanFactory {

    private final static Map<String, Object> BEAN_MAP = new ConcurrentHashMap<>();

    public static void addBean(String beanName, Object bean) {
        BEAN_MAP.put(beanName, bean);
    }

    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        Map<String, T> map = new HashMap<>();
        BEAN_MAP.forEach((beanName, bean) -> {
            if (type.isAssignableFrom(bean.getClass())) {
                map.put(beanName, (T)bean);
            }
        });
        return map;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> type) {
        for (Entry<String, Object> entry : BEAN_MAP.entrySet()) {
            if (type.isAssignableFrom(entry.getValue().getClass())) {
                return (T)entry.getValue();
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        return (T)BEAN_MAP.get(beanName);
    }

    public static Map<String, Object> getAllBean() {
        return BEAN_MAP;
    }

}
