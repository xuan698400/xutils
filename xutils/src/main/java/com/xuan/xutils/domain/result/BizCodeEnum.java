package com.xuan.xutils.domain.result;

import java.text.MessageFormat;

/**
 * 业务错误码样板枚举
 * <p>
 * Created by xuan on 17/8/2.
 */
public enum BizCodeEnum {
    /**
     * bizCode的格式一般我们定义为: 业务域范围前缀 + 编码
     */
    PARAM_IS_EMPTY("common_001", "参数[{0}]不能为空."),
    DATABASE_OPERATE_FAIL("common_002", "数据库操作失败,提示[{0}]."),;

    private String bizCode;
    private String msg;

    BizCodeEnum(String bizCode, String msg) {
        this.bizCode = bizCode;
        this.msg = msg;
    }

    public String getBizCode() {
        return bizCode;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 构建一个友好提示
     *
     * @param params
     * @return
     */
    public String buildMsg(Object... params) {
        if (params != null) {
            return MessageFormat.format(msg, params);
        }
        return msg;
    }

}
