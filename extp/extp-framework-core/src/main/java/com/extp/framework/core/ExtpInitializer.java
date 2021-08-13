package com.extp.framework.core;

import com.extp.framework.core.log.LogAdapter;
import com.extp.framework.core.log.LogAdapterFactory;
import com.extp.framework.core.plugin.PluginManager;

/**
 * @author xuan
 * @since 2021/8/13
 */
public class ExtpInitializer {

    private final static LogAdapter LOG = LogAdapterFactory.getLogAdapter();

    private static final ExtpInitializer INSTANCE = new ExtpInitializer();

    private ExtpInitializer() {

    }

    public static ExtpInitializer getInstance() {
        return INSTANCE;
    }

    private static volatile boolean initializing = false;

    private static volatile boolean finished = false;


    public void init() {
        synchronized (this) {

            //正在初始化中，循环等待
            while (initializing && !finished) {
                doWait(1000);
            }

            //已经初始化完成直接返回，防止重复初始化
            if (initializing && finished) {
                return;
            }

            if (!initializing) {
                initializing = true;
                doInit();
                finished = true;
            }
        }
    }

    private void doInit(){
        PluginManager.getInstance().init();
        FunctionManager.getInstance().init();
    }

    private void doWait(long timeout) {
        try {
            wait(timeout);
        } catch (InterruptedException ie) {
            LOG.error("ExtpInitializer_doWait_InterruptedException. timeout:" + timeout);
        }
    }

}
