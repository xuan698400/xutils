package com.xuan.mix.bt.cmd;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuan
 * @since 2021/9/1
 */
public class Main {

    public static void main(String[] args) throws Exception {
        //String[] cmds = new String[] {"/bin/sh", "-c", "ps -ef && ls && pwd"};
        String[] cmds = new String[] {"/bin/sh", "-c", "ps -ef | grep java"};
        String result = ShellHelper.execute(cmds, new ArrayList<>());
        System.out.println(result);
    }
}
