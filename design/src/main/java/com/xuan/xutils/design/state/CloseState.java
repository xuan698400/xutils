package com.xuan.xutils.design.state;

/**
 * @author xuan
 * @since 2021/9/5
 */
public class CloseState extends State {

    @Override
    protected void open() {
        context.setCurrentState(Context.OPEN);
        context.open();
    }

    @Override
    protected void close() {
        System.out.println("CloseState_close");
    }

}
