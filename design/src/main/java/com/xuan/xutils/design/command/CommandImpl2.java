package com.xuan.xutils.design.command;

/**
 * @author xuan
 * @since 2021/9/5
 */
public class CommandImpl2 implements Command {
    private Receiver receiver;

    public CommandImpl2(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.doSomething();
    }

}
