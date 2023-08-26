package com.xuan.xutils.design.state;

/**
 * @author xuan
 * @since 2021/9/5
 */
public abstract class State {

    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    protected abstract void open();

    protected abstract void close();
}
