package com.xuan.xutils.tools.cmd.core;

import com.xuan.xutils.tools.cmd.core.command.Command;

/**
 * 命令接受者，即实际处理命令方
 *
 * @author xuan
 * @since 2023/8/24
 */
public interface CommandReceiver {

    /**
     * 执行命令
     *
     * @param command 命令
     * @return 执行结果
     */
    CommandResult execute(Command command);
}
