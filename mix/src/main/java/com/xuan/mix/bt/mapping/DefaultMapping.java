package com.xuan.mix.bt.mapping;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author xuan
 * @since 2021/9/14
 */
public class DefaultMapping implements Mapping {

    @Override
    public Map<String, Object> mapping(Object fromObj, MappingConfig config) {

        Map<String, Object> toMap = new HashMap<>();

        for (MappingRule mappingRule : config.getRuleList()) {
            dealMappingRule(toMap, fromObj, mappingRule);
        }

        return toMap;
    }

    private void dealMappingRule(Map<String, Object> toMap, Object fromObj, MappingRule rule) {
        try {
            MappingField mf = getValueFromObj(fromObj, rule.getFromObjAttrLevel());

            if (null == mf) {
                return;
            }

            putValueToMap(toMap, rule.getToObjAttrLevel(), mf);
        } catch (Exception e) {
            //单个规则处理失败，忽略，但这里最好记录日志
            e.printStackTrace();
        }
    }

    private MappingField getValueFromObj(Object fromObj, List<String> fromObjAttrLevel) {
        Iterator<String> iterator = fromObjAttrLevel.iterator();
        Object obj = fromObj;
        MappingField mf = null;
        while (iterator.hasNext()) {
            String fieldName = iterator.next();
            mf = MappingField.of(obj, fieldName);

            //如果当前值是基本类型了，单配置还要往下取属性，判定为配置不合法，返回异常
            if (mf.isValuePrimitive() && iterator.hasNext()) {
                throw new MappingException(
                    "Value is primitive, but the config want to get the property down. The "
                        + "config is illegal. "
                        + mf.toString());
            }
            obj = mf.getValue();
        }
        return mf;
    }

    private void putValueToMap(Map<String, Object> toMap, List<String> toObjAttrLevel, MappingField mf) {
        Iterator<String> iterator = toObjAttrLevel.iterator();
        Map<String, Object> map = toMap;
        while (iterator.hasNext()) {
            String fieldName = iterator.next();
            if (iterator.hasNext()) {
                //如果还有下一级，这里需要套一层Map进去
                Map<String, Object> subMap = new HashMap<>();
                map.put(fieldName, subMap);
                map = subMap;
            } else {
                //没有下一级，把值放进去
                map.put(fieldName, mf.getValue());
            }
        }
    }

}
