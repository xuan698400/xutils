package com.xuan.xutils.tools.cmd.core.command;

import com.xuan.xutils.tools.cmd.core.CommandResult;

/**
 * 命令
 *
 * @author xuan
 * @since 2023/8/24
 */
public interface Command {

    /**
     * 执行
     *
     * @return 执行结果
     */
    CommandResult execute();
}
