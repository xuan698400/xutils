package com.xuan.xutils.tools.cmd.core.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用来执行shell命令
 *
 * @author xuan
 * @since 2020/10/14
 */
public class ShellUtils {

    private final static Logger log = LoggerFactory.getLogger(ShellUtils.class);

    /**
     * 单个执行命令
     *
     * @param command    命令
     * @param resultList 多行结果
     * @return 所有结果
     */
    public static String execute(String command, List<String> resultList) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            return readStringFromProcess(process, resultList);
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * 批量执行命令
     *
     * @param commands   批量命令
     * @param resultList 多行结果
     * @return 所有结果
     */
    public static String execute(String[] commands, List<String> resultList) {
        try {
            Process process = Runtime.getRuntime().exec(commands);
            return readStringFromProcess(process, resultList);
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * 处理命令后返回结果
     *
     * @param process    命令处理结果
     * @param resultList 存在多行，这里逐行返回
     * @return 整体结果
     */
    private static String readStringFromProcess(Process process, List<String> resultList) {
        return readStringFromInputStream(process.getInputStream(), resultList);
    }

    /**
     * 从输入流获取文本
     *
     * @param inputStream    输入流
     * @param resultLineList 存在多行，这里逐行返回
     * @return 返回输入流文本
     */
    private static String readStringFromInputStream(InputStream inputStream, List<String> resultLineList) {

        try (BufferedInputStream bis = new BufferedInputStream(inputStream);
             BufferedReader br = new BufferedReader(new InputStreamReader(bis))) {

            String lineStr;
            StringBuilder sb = new StringBuilder();
            while (null != (lineStr = br.readLine())) {
                sb.append(lineStr);
                sb.append("\n");
                resultLineList.add(lineStr);
            }
            return sb.toString();
        } catch (Exception e) {
            log.error("read string from inputStream exception. ", e);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String[] cmds = new String[] {"/bin/sh", "-c", "cd /Users/xuan && ls"};
        String result = ShellUtils.execute(cmds, new ArrayList<>());
        System.out.println(result);
    }

}
