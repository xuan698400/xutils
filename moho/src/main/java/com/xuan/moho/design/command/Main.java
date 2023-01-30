package com.xuan.moho.design.command;

/**
 * @author xuan
 * @since 2021/9/5
 */
public class Main {

    public static void main(String[] args) {

        Receiver receiver1 = new RecevierImpl1();
        Command command1 = new CommandImpl1(receiver1);

        Invoker invoker = new Invoker();
        invoker.setCommand(command1);
        invoker.action();

        Receiver receiver2 = new RecevierImpl2();
        Command command2 = new CommandImpl1(receiver2);
        invoker.setCommand(command2);
        invoker.action();
    }

}
