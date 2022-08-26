package com.xuan.hitools.cmd;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 用来执行shell命令
 *
 * @author xuan
 * @since 2020/10/14
 */
public class ShellHelper {

    public static String execute(String command, List<String> resultList) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            return readStringFromProcess(process, resultList);
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public static String execute(String[] commands, List<String> resultList) {
        try {
            Process process = Runtime.getRuntime().exec(commands);
            return readStringFromProcess(process, resultList);
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    private static String readStringFromProcess(Process process, List<String> resultList) {
        return readStringFromInputStream(process.getInputStream(), resultList);
    }

    private static String readStringFromInputStream(InputStream inputStream, List<String> resultList) {

        try (BufferedInputStream bis = new BufferedInputStream(inputStream);
             BufferedReader br = new BufferedReader(new InputStreamReader(bis))) {

            String lineStr;
            StringBuilder sb = new StringBuilder();
            while (null != (lineStr = br.readLine())) {
                sb.append(lineStr);
                sb.append("\n");
                resultList.add(lineStr);
            }
            return sb.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) throws Exception {
        String[] cmds = new String[] {"/bin/sh", "-c", "ps -ef && ls && pwd"};
        String result = ShellHelper.execute(cmds, new ArrayList<>());
        System.out.println(result);
    }

}
