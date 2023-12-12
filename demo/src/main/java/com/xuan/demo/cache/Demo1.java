package com.xuan.demo.cache;

/**
 * 常用缓存策略
 *
 * @author xuan
 * @since 2023/12/11
 */
public class Demo1 {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            getValue("K1");
        }
        System.out.println("总耗时:" + (System.currentTimeMillis() - start));
    }

    private static String getValue(String key) {
        long start = System.currentTimeMillis();

        //1、从缓存中尝试获取
        String value = MockCacheHelper.get(key);

        if (null == value) {

            //2、Miss缓存，回源到数据库查询
            value = MockDbDbHelper.load(key);

            //3、设置缓存
            if (null != value) {
                MockCacheHelper.put(key, value);
            }
        }

        System.out.println("单条记录耗时:" + (System.currentTimeMillis() - start));
        return value;
    }

}
