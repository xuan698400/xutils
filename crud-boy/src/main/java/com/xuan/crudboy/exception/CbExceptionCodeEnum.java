package com.xuan.crudboy.exception;

/**
 * 常用错误码枚举，系统可参考该枚举定义，实现自己业务系统特有的异常码
 *
 * @author xuan
 * @since 2020/1/10
 */
public enum CbExceptionCodeEnum implements CbExceptionCode {

    /**
     * 业务类异常
     */
    BIZ_COMMON_EXCEPTION("BIZ", "COMMON_EXCEPTION", "业务异常：%s"),
    BIZ_PARAM_NULL("BIZ", "PARAM_NULL", "参数不能为NULL异常：%s"),
    BIZ_PARAM_EMPTY("BIZ", "PARAM_EMPTY", "参数不能为空异常：%s"),
    BIZ_PARAM_INVALID("BIZ", "PARAM_INVALID", "参数不合法：%s"),

    /**
     * 数据库类异常
     */
    DB_COMMON_EXCEPTION("DB", "COMMON_EXCEPTION", "数据异常：%s"),
    DB_INSERT_EXCEPTION("DB", "INSERT_EXCEPTION", "数据库插入异常：%s"),
    DB_DELETE_EXCEPTION("DB", "DELETE_EXCEPTION", "数据库删除异常：%s"),
    DB_UPDATE_EXCEPTION("DB", "UPDATE_EXCEPTION", "数据库更新异常：%s"),
    DB_SELECT_EXCEPTION("DB", "SELECT_EXCEPTION", "数据库查询异常：%s"),

    /**
     * RPC调用类异常
     */
    RPC_COMMON_EXCEPTION("RPC", "COMMON_EXCEPTION", "RPC调用异常：%s"),
    RPC_TIMEOUT_EXCEPTION("RPC", "TIMEOUT_EXCEPTION", "RPC调用超时异常：%s"),
    RPC_CONNECT_EXCEPTION("RPC", "CONNECT_EXCEPTION", "RPC调用连接异常：%s"),
    RPC_SERIALIZE_EXCEPTION("RPC", "SERIALIZE_EXCEPTION", "RPC调用序列化异常：%s"),
    ;

    /**
     * 一级异常码
     */
    private String code;

    /**
     * 二级异常码
     */
    private String subCode;

    /**
     * 错误提示模版，支持%s占位，在使用时可以设置参数进行替换
     */
    private String msg;

    CbExceptionCodeEnum(String code, String subCode, String msg) {
        this.code = code;
        this.subCode = subCode;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getSubCode() {
        return subCode;
    }

    @Override
    public String getMsg(Object... args) {
        return String.format(msg, args);
    }

}
