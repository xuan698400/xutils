package com.xuan.crudboy;

import com.xuan.crudboy.config.CbConfigManager;
import com.xuan.crudboy.config.core.CbDefaultConfigManager;

/**
 * @author xuan
 * @since 2023/11/10
 */
public class CrudBoy {

    private final static CrudBoy INSTANCE = new CrudBoy();

    private CrudBoy() {

    }

    public static CrudBoy getInstance() {
        return INSTANCE;
    }

    private CbConfigManager configManager = new CbDefaultConfigManager();

    public void init() {
        configManager.init();
    }

    public CbConfigManager getConfigManager() {
        return configManager;
    }

}
