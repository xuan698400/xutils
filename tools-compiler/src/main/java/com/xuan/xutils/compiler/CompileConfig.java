package com.xuan.xutils.compiler;

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
        + ".xutils_compiler_cache_dir" + File.separator;

    /**
     * 获取缓存文件路径
     *
     * @return 缓存文件路径
     */
    public String getCacheDir() {
        return cacheDir;
    }

    /**
     * 设置缓存文件路径
     *
     * @param cacheDir 缓存文件路径
     */
    public void setCacheDir(String cacheDir) {
        this.cacheDir = cacheDir;
    }

}
