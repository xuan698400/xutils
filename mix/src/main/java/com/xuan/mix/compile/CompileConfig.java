package com.xuan.mix.compile;

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
    private String compileDir = System.getProperty("user.dir") + File.separator
        + ".xUtils_compile_dir" + File.separator;

    public String getCompileDir() {
        return compileDir;
    }

    public void setCompileDir(String compileDir) {
        this.compileDir = compileDir;
    }

}
