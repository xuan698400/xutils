package com.xuan.mix.bt.status.impl;

import com.xuan.mix.bt.status.Level;
import com.xuan.mix.bt.status.Status;
import com.xuan.mix.bt.status.StatusChecker;

/**
 * 内存使用检查
 * <p>
 * Created by xuan on 17/7/29.
 */
public class MemoryStatusChecker implements StatusChecker {

    @Override
    public Status check() {
        Runtime runtime = Runtime.getRuntime();
        long freeMemory = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();
        long maxMemory = runtime.maxMemory();
        // 剩余空间小于2M报警
        boolean ok = (maxMemory - (totalMemory - freeMemory) > 2048);
        String msg = "max:" + (maxMemory / 1024 / 1024) + "M,total:"
            + (totalMemory / 1024 / 1024) + "M,used:" + ((totalMemory / 1024 / 1024) - (freeMemory / 1024 / 1024))
            + "M,free:" + (freeMemory / 1024 / 1024) + "M";
        return new Status(ok ? Level.OK : Level.WARN, msg);
    }

}