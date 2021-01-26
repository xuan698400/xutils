package com.xuan.common.concurrent;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuan
 * @since 2021/1/26
 */
public class ThreadLocalUtil {

    private static ThreadLocal<Map<String, Object>> BIZ_THREAD_LOCAL = ThreadLocal.withInitial(() -> new HashMap<>());

    public static Map<String, Object> get() {
        return ThreadLocalUtil.BIZ_THREAD_LOCAL.get();
    }

    public static void set(final Map<String, Object> keyValueMap) {
        ThreadLocalUtil.BIZ_THREAD_LOCAL.set(keyValueMap);
    }

    public static void remove() {
        ThreadLocalUtil.BIZ_THREAD_LOCAL.remove();
    }

}
