package com.xuan.xutils.design.command;

/**
 * @author xuan
 * @since 2021/9/5
 */
public class Invoker {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void action() {
        this.command.execute();
    }

}
