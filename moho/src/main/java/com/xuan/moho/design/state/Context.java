package com.xuan.moho.design.state;

/**
 * @author xuan
 * @since 2021/9/5
 */
public class Context {
    public static final State OPEN = new OpenState();
    public static final State CLOSE = new CloseState();

    private State currentState;

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
        this.currentState.setContext(this);
    }

    public void open() {
        this.currentState.open();
    }

    public void close() {
        this.currentState.close();
    }

}
