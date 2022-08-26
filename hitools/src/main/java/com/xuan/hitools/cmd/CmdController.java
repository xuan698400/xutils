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

        //如果是文件查看，需要识别文件类型
        if ("ls".equals(cmd)) {
            fillFileType(result, path, resultList);
        }
        return result;
    }

    private void fillFileType(Map<String, Object> result, String path, List<String> fileList) {
        if (null == fileList) {
            return;
        }

        Map<String, String> file2ColorMap = new HashMap<>();
        for (String file : fileList) {
            if (file.endsWith(".sh")) {
                file2ColorMap.put(file, "#8ae234");
                continue;
            }

            String sp = path.endsWith("/") ? "" : "/";
            File f = new File(path + sp + file);
            if (f.isDirectory()) {
                file2ColorMap.put(file, "#729fcf");
            } else {
                file2ColorMap.put(file, "#ffffff");
            }
        }
        result.put("file2ColorMap", file2ColorMap);
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

}
