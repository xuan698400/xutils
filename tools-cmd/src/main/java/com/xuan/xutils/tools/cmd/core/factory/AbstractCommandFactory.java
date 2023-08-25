package com.xuan.xutils.tools.cmd.core.factory;

import com.xuan.xutils.tools.cmd.core.command.Command;

/**
 * 命令生产工厂基类，方便后续扩展
 *
 * @author xuan
 * @since 2023/8/25
 */
public abstract class AbstractCommandFactory implements CommandFactory {

    @Override
    public Command getCommand(String input, String path) {
        return doGetCommand(input, path);
    }

    /**
     * 具体工厂实现
     *
     * @param input 用户输入
     * @param path  当前目录
     * @return 命令
     */
    protected abstract Command doGetCommand(String input, String path);
}
