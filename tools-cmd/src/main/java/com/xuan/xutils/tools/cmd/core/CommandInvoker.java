package com.xuan.xutils.tools.cmd.core;

import com.xuan.xutils.tools.cmd.core.command.Command;

/**
 * 命令执行者
 *
 * @author xuan
 * @since 2023/8/24
 */
public class CommandInvoker {

    /**
     * 命令
     */
    private Command command;

    /**
     * 设置命令
     *
     * @param command 命令
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * 执行命令
     *
     * @return 执行结果
     */
    public CommandResult invoke() {
        return command.execute();
    }

}
