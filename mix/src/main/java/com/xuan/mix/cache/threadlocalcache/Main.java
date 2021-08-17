package com.xuan.mix.cache.threadlocalcache;

/**
 * @author xuan
 * @since 2021/6/11
 */
public class Main {

    public static class ValueObject {

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) throws Throwable {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            step1();
            step2();
        }
        System.out.println("----:" + (System.currentTimeMillis() - start));
    }

    private static void step1() throws Throwable {
        ThreadLocalCacheInvoker.call("xxx", "124", (key) -> {
            return getValueObject();
        });
    }

    private static void step2() throws Throwable {
        ThreadLocalCacheInvoker.call("xxx", "124", (key) -> {
            return getValueObject();
        });
    }

    private static ValueObject getValueObject() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {

        }

        return new ValueObject();
    }
}
