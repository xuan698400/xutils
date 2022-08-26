package com.xuan.hitools.cmd;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuan
 * @since 2020/10/14
 */
@RestController
@RequestMapping("hitools/cmd/")
public class CmdController {

    @RequestMapping("exec")
    public Map<String, Object> exec(String path, String cmd) {
        Map<String, Object> result = new HashMap<>();

        String[] cmds = new String[] {"/bin/sh", "-c", "cd " + path + " && " + cmd};
        List<String> resultList = new ArrayList<>();
        ShellHelper.execute(cmds, resultList);

        result.put("success", resultList);
        result.put("data", resultList);
        result.put("ip", getIp());

        fillFileTypeMap(cmd, result, path, resultList);
        fillFileWidth(cmd, result, resultList);
        return result;
    }

    private void fillFileWidth(String cmd, Map<String, Object> result, List<String> fileList) {
        if ("!ls".equals(cmd) || null == fileList) {
            return;
        }

        int maxFileNameLength = 0;
        for (String fileName : fileList) {
            if (getStringLength(fileName) > maxFileNameLength) {
                maxFileNameLength = fileName.length();
            }
        }
        result.put("maxFileNameLength", maxFileNameLength);
    }

    private void fillFileTypeMap(String cmd, Map<String, Object> result, String path, List<String> fileList) {
        if ("!ls".equals(cmd) || null == fileList) {
            return;
        }

        Map<String, String> fileTypeMap = new HashMap<>();
        for (String fileName : fileList) {
            if (fileName.endsWith(".sh")) {
                fileTypeMap.put(fileName, "sh");
                continue;
            }

            String sp = path.endsWith("/") ? "" : "/";
            File f = new File(path + sp + fileName);
            if (f.isDirectory()) {
                fileTypeMap.put(fileName, "dir");
            } else {
                fileTypeMap.put(fileName, "file");
            }
        }
        result.put("fileTypeMap", fileTypeMap);
    }

    private String getIp() {
        try {
            InetAddress ip = Inet4Address.getLocalHost();
            return ip.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "unknown-host";
    }

    private int getStringLength(String str) {
        String[] strs = str.split("\n");
        String maxStr = "";
        for (String s : strs) {
            if ("".equals(maxStr)) {
                maxStr = s;
                continue;
            }

            if (s.length() > maxStr.length()) {
                maxStr = s;
            }
        }

        int length = 0;
        for (int i = 0, n = maxStr.length(); i < n; i++) {
            int c = (int)maxStr.charAt(i);
            if (c < 40869 && c >= 19968) {
                length += 2;
            } else {
                length++;
            }
        }
        return Math.min(length, 40);
    }

}
