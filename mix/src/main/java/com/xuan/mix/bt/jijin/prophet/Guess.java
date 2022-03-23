package com.xuan.mix.bt.jijin.prophet;

/**
 * @author xuan
 * @since 2022/3/13
 */
public class Guess {

    public static boolean isUp() {
        double random = Math.random();
        return random > 0.5D;
    }

}
