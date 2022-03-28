package com.xuan.mix.protocal.kv;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuan
 * @since 2022/3/28
 */
public class Main {
    public static void main(String[] args) {
        Map<String, String> featureMap = new HashMap<>();
        featureMap.put("a", "e:5;5");
        featureMap.put("b", "v");
        featureMap.put("d", "{\"dd\":\"ff\"}");
        String str = KvHelper.toString(featureMap);
        System.out.println(str);
        System.out.println(KvHelper.toMap(str));
    }

}
