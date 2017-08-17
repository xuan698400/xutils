package com.xuan.xutils.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 一些基本的字符串,集合,数字等校验工具
 *
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2017-08-01 上午9:44:21 $
 */
public abstract class XBeanUtils {
    /**
     * Map --> Bean : 利用Introspector,PropertyDescriptor实现 Map --> Bean
     * map 中value 的类型必须对应到Bean中的类型
     *
     * @param map
     * @param beanClass 必须提供公共的默认构造函数
     * @param <T>
     * @return
     */
    public static <T> T map2Bean(Map<String, Object> map, Class<T> beanClass) {
        T obj;
        try {
            obj = beanClass.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (!map.containsKey(key)) {
                    continue;
                }
                Object value = map.get(key);
                // 得到property对应的setter方法
                Method setter = property.getWriteMethod();
                setter.invoke(obj, value);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    /**
     * Bean --> Map 利用Introspector和PropertyDescriptor 将Bean --> Map,对getClass 做了过滤
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> bean2Map(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (key.equals("class")) {
                    continue;
                }
                // 得到property对应的getter方法
                Method getter = property.getReadMethod();
                Object value = getter.invoke(obj);
                map.put(key, value);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return map;
    }

}
