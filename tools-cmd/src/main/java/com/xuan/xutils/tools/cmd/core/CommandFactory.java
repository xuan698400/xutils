package com.xuan.xutils.tools.cmd.core;

import com.xuan.xutils.tools.cmd.core.command.Command;
import com.xuan.xutils.tools.cmd.core.common.CmdException;
import com.xuan.xutils.tools.cmd.core.common.Constants;
import com.xuan.xutils.tools.cmd.core.common.StringUtils;

/**
 * 命令工厂
 *
 * @author xuan
 * @since 2023/8/24
 */
public class CommandFactory {

    /**
     * 根据用户的输入，获取一个命令
     *
     * @param inputCmd 用户输入命令
     * @param path     当前操作路径
     * @return 命令
     */
    public static Command getCommand(String inputCmd, String path) {
        if (StringUtils.isEmpty(inputCmd)) {
            throw new CmdException(Constants.EXCEPTION_CODE_PARAM_INVALID, "用户输入命令不能为空");
        }
        if (StringUtils.isEmpty(path)) {
            throw new CmdException(Constants.EXCEPTION_CODE_PARAM_INVALID, "当前操作路径不能为空");
        }

        return null;
    }

}
