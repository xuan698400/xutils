package com.xuan.demo.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟数据库读/写
 *
 * @author xuan
 * @since 2023/12/11
 */
public class MockDbDbHelper {

    private final static Map<String, String> DB_DATA = new HashMap<>();

    static {
        DB_DATA.put("K1", "V1");
        DB_DATA.put("K2", "V2");
        DB_DATA.put("K3", "V3");
    }

    public static String load(String key) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            //ignore
        }

        return DB_DATA.get(key);
    }

}
