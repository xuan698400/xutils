package com.xuan.xutils.tools.cmd.core.factory;

import com.xuan.xutils.tools.cmd.core.command.Command;

/**
 * 命令生产工厂
 *
 * @author xuan
 * @since 2023/8/25
 */
public interface CommandFactory {

    /**
     * 根据用户输入，构建一个命令
     *
     * @param input 用户输入
     * @param path  当前目录
     * @return 命令
     */
    Command getCommand(String input, String path);
}
