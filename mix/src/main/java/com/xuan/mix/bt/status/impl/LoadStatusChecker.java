package com.xuan.mix.bt.status.impl;


import com.xuan.xutils.bt.status.Level;
import com.xuan.xutils.bt.status.Status;
import com.xuan.xutils.bt.status.StatusChecker;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;

/**
 * 负载状态检查
 * <p>
 * Created by xuan on 17/7/29.
 */
public class LoadStatusChecker implements StatusChecker {

    @Override
    public Status check() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        double load;
        try {
            Method method = OperatingSystemMXBean.class.getMethod("getSystemLoadAverage", new Class<?>[0]);
            load = (Double) method.invoke(operatingSystemMXBean, new Object[0]);
        } catch (Throwable e) {
            load = -1;
        }
        int cpu = operatingSystemMXBean.getAvailableProcessors();
        return new Status(load < 0 ? Level.UNKNOWN : (load < cpu ? Level.OK : Level.WARN), (load < 0 ? "" : "load:" + load + ",") + "cpu:" + cpu);
    }

}