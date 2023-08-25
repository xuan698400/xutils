package com.xuan.xutils.tools.cmd.core;

import com.xuan.xutils.tools.cmd.core.command.Command;

/**
 * 接受者基类
 *
 * @author xuan
 * @since 2023/8/24
 */
public abstract class AbstractCommandReceiver implements CommandReceiver {

    @Override
    public CommandResult execute(Command command) {
        preExecute();
        CommandResult result = doExecute(command);
        postExecute(result);
        return result;
    }

    /**
     * 子类实现，执行命令
     *
     * @param command 命令
     * @return 执行结果
     */
    protected abstract CommandResult doExecute(Command command);

    /**
     * 执行前操作，如果需要，可以复写
     */
    protected void preExecute() {}

    /**
     * 执行后操作，如果需要，可以复写
     */
    protected void postExecute(CommandResult result) {}
}
