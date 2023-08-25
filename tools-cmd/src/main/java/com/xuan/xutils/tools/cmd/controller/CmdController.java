package com.xuan.xutils.tools.cmd.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xuan.xutils.tools.cmd.core.common.Constants;
import com.xuan.xutils.tools.cmd.core.common.FileTypeEnum;
import com.xuan.xutils.tools.cmd.core.common.IpUtils;
import com.xuan.xutils.tools.cmd.core.common.ShellUtils;
import com.xuan.xutils.tools.cmd.core.common.StringUtils;
import com.xuan.xutils.tools.cmd.controller.model.CmdResult;
import com.xuan.xutils.tools.cmd.controller.model.ExecInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 命令执行API
 *
 * @author xuan
 * @since 2020/10/14
 */
@RestController
@RequestMapping("tools/cmd/")
public class CmdController {

    /**
     * 在指定路径下执行命令，并返回执行结果
     *
     * @param path 指定路径
     * @param cmd  命令
     * @return 执行结果
     */
    @RequestMapping("exec")
    public CmdResult exec(String path, String cmd) {

        ExecInfo execInfo = new ExecInfo();

        String[] cmds = new String[] {"/bin/sh", "-c", "cd " + path + " && " + cmd};
        List<String> resultList = new ArrayList<>();
        ShellUtils.execute(cmds, resultList);

        execInfo.setResultList(resultList);
        execInfo.setIp(IpUtils.getLocalIp());
        execInfo.setMaxFileNameLength(getMaxFileNameLength(cmd, resultList));
        execInfo.setFileTypeMap(getFillFileTypeMap(cmd, path, resultList));
        execInfo.setUserName(Constants.ADMIN);
        execInfo.setPath(path);

        return CmdResult.success(execInfo);
    }

    private int getMaxFileNameLength(String cmd, List<String> fileNameList) {
        if (!Constants.LS.equals(cmd) || null == fileNameList) {
            return -1;
        }

        int maxFileNameLength = 0;
        for (String fileName : fileNameList) {
            if (StringUtils.getStringLength(fileName) > maxFileNameLength) {
                maxFileNameLength = fileName.length();
            }
        }
        return maxFileNameLength;
    }

    private Map<String, String> getFillFileTypeMap(String cmd, String path, List<String> fileNameList) {
        if (!Constants.LS.equals(cmd) || null == fileNameList) {
            return new HashMap<>();
        }

        Map<String, String> fileTypeMap = new HashMap<>();
        for (String fileName : fileNameList) {

            if (fileName.endsWith(Constants.DOT + FileTypeEnum.SH.getCode())) {
                fileTypeMap.put(fileName, FileTypeEnum.SH.getCode());
                continue;
            }

            String sp = path.endsWith(Constants.SLASH) ? Constants.EMPTY : Constants.SLASH;
            File f = new File(path + sp + fileName);
            if (f.isDirectory()) {
                fileTypeMap.put(fileName, FileTypeEnum.DIR.getCode());
            } else {
                fileTypeMap.put(fileName, FileTypeEnum.FILE.getCode());
            }
        }

        return fileTypeMap;
    }

}
