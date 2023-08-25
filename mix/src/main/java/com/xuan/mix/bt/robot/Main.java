package com.xuan.mix.bt.robot;

import java.awt.*;

/**
 * @author xuan
 * @since 2023/4/6
 */
public class Main {

    private static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) throws Exception {
        robot.wait(5 * 1000);
    }

}
