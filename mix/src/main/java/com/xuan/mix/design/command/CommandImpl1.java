package com.xuan.mix.design.command;

/**
 * @author xuan
 * @since 2021/9/5
 */
public class CommandImpl1 implements Command {

    private Receiver receiver;

    public CommandImpl1(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.doSomething();
    }

}
