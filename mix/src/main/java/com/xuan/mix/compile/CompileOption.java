package com.xuan.mix.compile;

import java.io.File;

/**
 * 编译时可选项
 *
 * @author xuan
 * @since 2020/10/19
 */
public class CompileOption {

    /**
     * 编译后class的目录
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
