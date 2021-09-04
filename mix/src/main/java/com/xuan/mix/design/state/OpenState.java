package com.xuan.mix.design.state;

/**
 * @author xuan
 * @since 2021/9/5
 */
public class OpenState extends State {

    @Override
    protected void open() {
        System.out.println("OpenState_open");
    }

    @Override
    protected void close() {
        context.setCurrentState(Context.CLOSE);
        context.close();
    }

}
