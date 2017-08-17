package com.xuan.xutils.cache;

import com.xuan.xutils.cache.provider.SimpleCache;
import org.junit.Assert;
import org.junit.Test;

/**
 * 缓存测试
 * <p>
 * Created by xuan on 17/7/31.
 */
public class CacheTest {

    @Test
    public void testAdd() {
        //创建缓存管理器,正式环境可以配置在Spring中等使用,单例既可
        CacheManager cacheManager = new DefaultCacheManager();
        cacheManager.addCache("test", new SimpleCache());

        //未放入缓存取不到
        Object v = cacheManager.getCache("test").get("testKey");
        Assert.assertNull(v);

        //放入缓存
        cacheManager.getCache("test").add("testKey", "xuan");

        //取缓存的值
        v = (String) cacheManager.getCache("test").get("testKey");
        Assert.assertNotNull(v);
        Assert.assertTrue("xuan".equals(v));

        System.out.println("++++++++++v:" + v);
    }

}
