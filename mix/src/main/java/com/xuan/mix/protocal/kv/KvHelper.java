package com.xuan.mix.protocal.kv;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * KV格式解析工具，格式例如：;a:b;m=1;
 *
 * @author xuan
 * @since 2020/11/17
 */
public class KvHelper {

    private static final String SP = ";";
    private static final String SSP = ":";
    /**
     * ;转译符号，用来区分kv对
     */
    private static final String R_SP = "#3A";
    /**
     * :转译符号，用来区分k和v
     */
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

    public static Map<String, String> toMap(String str) {
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

    public static String merge(String oldKvStr, String newKvStr) {
        return KvHelper.toString(merge(KvHelper.toMap(oldKvStr), KvHelper.toMap(newKvStr)));
    }

    public static Map<String, String> merge(Map<String, String> oldKvMap, Map<String, String> newKvMap) {
        if (null == oldKvMap || oldKvMap.isEmpty()) {
            return new HashMap<>(newKvMap);
        }
        if (null == newKvMap || newKvMap.isEmpty()) {
            return new HashMap<>(oldKvMap);
        }
        Map<String, String> mergeMap = new HashMap<>();
        mergeMap.putAll(oldKvMap);
        mergeMap.putAll(newKvMap);
        return mergeMap;
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

}
