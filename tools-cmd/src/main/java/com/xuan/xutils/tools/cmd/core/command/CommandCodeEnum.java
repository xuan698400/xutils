package com.xuan.xutils.tools.cmd.core.command;

/**
 * 命令枚举
 *
 * @author xuan
 * @since 2023/8/24
 */
public enum CommandCodeEnum {

    //
    CD("cd", "打开文件夹"),
    LS("ls", "查看文件列表"),
    PWD("pwd", "查看当前目录路径");

    private String code;

    private String name;

    CommandCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
