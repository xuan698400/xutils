package com.xuan.mix.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * sell命令执行工具类
 *
 * @author xuan
 * @since 2020/10/16
 */
public class ShellUtils {

    public static String executeResultStr(String command) {
        return linesToString(execute(command));
    }

    public static String executeResultStr(String[] commands) {
        return linesToString(execute(commands));
    }

    public static List<String> execute(String command) {
        String[] cmds = new String[] {command};
        return execute(cmds);
    }

    public static List<String> execute(String[] commands) {
        try {
            Process process = Runtime.getRuntime().exec(commands);
            return readLinesFromProcess(process);
        } catch (IOException e) {
            List<String> lines = new ArrayList<>();
            lines.add(e.getMessage());
            return lines;
        }
    }

    private static List<String> readLinesFromProcess(Process process) {

        List<String> lines = new ArrayList<>();

        try (BufferedInputStream bis = new BufferedInputStream(process.getInputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(bis))) {

            String lineStr;
            while (null != (lineStr = br.readLine())) {
                lines.add(lineStr);
            }
            return lines;
        } catch (Exception e) {
            lines.add(e.getMessage());
        }
        return lines;
    }

    private static String linesToString(List<String> lines) {
        if (null == lines || lines.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        lines.forEach(line -> {
            sb.append(line);
            sb.append("\n");
        });
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String[] cmds = new String[] {"/bin/sh", "-c", "ps -ef && ls && pwd"};
        String result = executeResultStr(cmds);
        System.out.println(result);

        //String[] cmds = new String[] {"ls"};
        //System.out.println(executeResultStr(cmds));
    }

}
