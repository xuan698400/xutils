package com.xuan.moho.tools.compiler;

import java.io.File;

/**
 * 编译配置项
 *
 * @author xuan
 * @since 2020/10/19
 */
public class CompileConfig {

    /**
     * 编译时需要临时输出文件目录
     */
    private String cacheDir = System.getProperty("user.dir") + File.separator
        + ".Moho_compiler_cache_dir" + File.separator;

    public String getCacheDir() {
        return cacheDir;
    }

    public void setCacheDir(String cacheDir) {
        this.cacheDir = cacheDir;
    }

}
