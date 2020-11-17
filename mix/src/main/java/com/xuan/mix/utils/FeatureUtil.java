package com.xuan.mix.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author xuan
 * @since 2020/11/17
 */
public class FeatureUtil {

    private static final String SP = ";";
    private static final String SSP = ":";

    private static final String R_SP = "#3A";
    private static final String R_SSP = "#3B";

    public static String toString(Map<String, String> featureMap) {
        StringBuilder sb = new StringBuilder();
        if (null != featureMap && !featureMap.isEmpty()) {
            sb.append(SP);
            Set<String> featureKeySet = new TreeSet<>(featureMap.keySet());
            for (String featureKey : featureKeySet) {
                String featureValue = featureMap.get(featureKey);
                if (isEmpty(featureValue) || isEmpty(featureValue)) {
                    continue;
                }
                sb.append(encode(featureKey)).append(SSP).append(encode(featureValue)).append(SP);
            }
        }
        return sb.toString();
    }

    public static final Map<String, String> toMap(String str) {
        Map<String, String> featureMap = new HashMap<>();

        if (isEmpty(str)) {
            return featureMap;
        }

        String[] arr = str.split(SP);
        for (String kv : arr) {
            if (isEmpty(kv)) {
                continue;
            }

            String[] ar = kv.split(SSP);
            if (ar.length != 2) {
                continue;
            }

            String key = decode(ar[0]);
            String val = decode(ar[1]);
            featureMap.put(key, val);
        }
        return featureMap;
    }

    private static boolean isEmpty(String str) {
        return null == str || str.trim().length() == 0;
    }

    private static String encode(String val) {
        return val.replaceAll(SP, R_SP).replaceAll(SSP, R_SSP);
    }

    private static String decode(String val) {
        return val.replaceAll(R_SP, SP).replaceAll(R_SSP, SSP);
    }

    public static void main(String[] args) {
        Map<String, String> featureMap = new HashMap<>();
        featureMap.put("a", "e:5;5");
        featureMap.put("b", "v");
        featureMap.put("d", "{\"dd\":\"ff\"}");
        String str = FeatureUtil.toString(featureMap);
        System.out.println(str);

        System.out.println(FeatureUtil.toMap(str));
    }

}
