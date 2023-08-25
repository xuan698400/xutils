package com.xuan.xutils.tools.cmd.core.receiver;

import com.xuan.xutils.tools.cmd.core.AbstractCommandReceiver;
import com.xuan.xutils.tools.cmd.core.command.Command;
import com.xuan.xutils.tools.cmd.core.CommandResult;

/**
 * 默认实现者
 *
 * @author xuan
 * @since 2023/8/24
 */
public class DefaultReceiver extends AbstractCommandReceiver {

    @Override
    protected CommandResult doExecute(Command command) {
        return null;
    }

}
