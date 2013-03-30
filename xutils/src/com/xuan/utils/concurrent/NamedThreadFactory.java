package com.xuan.utils.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一个可以生成带有自定义前缀线程名的线程工厂<br />
 * 例如： 线程名为xuan_thread-5中xuan为自定义前缀,5表示是生产的第5个线程
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:31:32 $
 */
public class NamedThreadFactory implements ThreadFactory {

    private static AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    /**
     * 构造方法
     * 
     * @param namePrefix
     *            自定义前缀
     */
    public NamedThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    /**
     * 覆写ThreadFactory中的newThread
     */
    @Override
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, namePrefix + " thread-" + threadNumber.getAndIncrement());
    }

}
