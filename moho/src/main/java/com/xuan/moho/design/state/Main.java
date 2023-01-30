package com.xuan.moho.design.state;

/**
 * @author xuan
 * @since 2021/9/5
 */
public class Main {

    public static void main(String[] args) {
        Context context = new Context();
        context.setCurrentState(Context.OPEN);
        context.open();
        context.close();
    }
}
