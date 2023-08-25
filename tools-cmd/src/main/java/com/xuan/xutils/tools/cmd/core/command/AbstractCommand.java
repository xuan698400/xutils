package com.xuan.xutils.tools.cmd.core.command;

import java.util.List;

import com.xuan.xutils.tools.cmd.core.CommandReceiver;
import com.xuan.xutils.tools.cmd.core.CommandResult;

/**
 * 命令抽象基类
 *
 * @author xuan
 * @since 2023/8/24
 */
public abstract class AbstractCommand implements Command {

    /**
     * 命令接受者
     */
    private CommandReceiver commandReceiver;

    /**
     * 脚本，例如：ls、cd、pwd
     */
    private String commandCode;

    /**
     * 脚本后带的参数，例如，cd aaa，那么这里就会有一个值叫aaa
     */
    private List<String> commandParams;

    @Override
    public CommandResult execute() {
        return commandReceiver.execute(this);
    }

    public CommandReceiver getCommandReceiver() {
        return commandReceiver;
    }

    public void setCommandReceiver(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public String getCommandCode() {
        return commandCode;
    }

    public void setCommandCode(String commandCode) {
        this.commandCode = commandCode;
    }

    public List<String> getCommandParams() {
        return commandParams;
    }

    public void setCommandParams(List<String> commandParams) {
        this.commandParams = commandParams;
    }

}
