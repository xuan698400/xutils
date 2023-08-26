package com.xuan.xutils.design.template;

/**
 * @author xuan
 * @since 2023/2/20
 */
public abstract class Game {

    public final void play() {

        //1、玩之前需要申请，例如跟老婆汇报
        playApply();

        //2、真正玩起来，至于玩什么，有很多中玩法
        doPlay();

        //3、玩好了结束回家
        goHome();
    }

    protected abstract void doPlay();

    private void playApply() {
        System.out.println("跟老婆申请出去玩");
    }

    private void goHome() {
        System.out.println("游玩结束回家");
    }
}
