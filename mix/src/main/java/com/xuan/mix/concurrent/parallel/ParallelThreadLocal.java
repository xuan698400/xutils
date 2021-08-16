package com.xuan.mix.concurrent.parallel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuan
 * @since 2021/8/16
 */
public class ParallelThreadLocal {
    private static ThreadLocal<Map<String, Object>> BIZ_THREAD_LOCAL = ThreadLocal.withInitial(() -> new HashMap<>());

    public static Map<String, Object> get() {
        return ParallelThreadLocal.BIZ_THREAD_LOCAL.get();
    }

    public static void set(final Map<String, Object> keyValueMap) {
        ParallelThreadLocal.BIZ_THREAD_LOCAL.set(keyValueMap);
    }

    public static void remove() {
        ParallelThreadLocal.BIZ_THREAD_LOCAL.remove();
    }

}
