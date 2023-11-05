package com.xuan.crudboy.exception;

/**
 * 这里定义了构建异常的工厂，业务系统可按自己要求自己构建异常工厂类
 * 系统构建异常最好都通过工厂类来获取，这样能够很好的收敛异常构建的方式
 *
 * @author xuan
 * @since 2021/7/12
 */
public class CbExceptionFactory {

    /**
     * 构建异常
     *
     * @param code   异常枚举
     * @param params 提示参数
     * @return 异常
     */
    public static CbException exception(CbExceptionCode code, Object... params) {
        return new CbException(code, code.getMsg(params));
    }

    /**
     * 构建异常
     *
     * @param code   异常枚举
     * @param e      Throwable
     * @param params 提示参数
     * @return 异常
     */
    public static CbException exception(CbExceptionCode code, Throwable e, Object... params) {
        return new CbException(code, code.getMsg(params), e);
    }

    /**
     * 构建业务通用异常
     * 错误码是自带的：BIZ_COMMON_EXCEPTION
     *
     * @param msg 提示参数
     * @return 业务通用异常
     */
    public static CbException bizException(String msg) {
        return CbExceptionFactory.exception(CbExceptionCodeEnum.BIZ_COMMON_EXCEPTION, msg);
    }

    /**
     * 构建业务通用异常
     * 错误码是自带的：BIZ_COMMON_EXCEPTION
     *
     * @param msg 提示参数
     * @param e   Throwable
     * @return 业务通用异常
     */
    public static CbException bizException(String msg, Throwable e) {
        return CbExceptionFactory.exception(CbExceptionCodeEnum.BIZ_COMMON_EXCEPTION, e, msg);
    }

    /**
     * 构建数据库通用异常
     * 错误码是自带的：DB_COMMON_EXCEPTION
     *
     * @param msg 提示参数
     * @return 数据库通用异常
     */
    public static CbException dbException(String msg) {
        return CbExceptionFactory.exception(CbExceptionCodeEnum.DB_COMMON_EXCEPTION, msg);
    }

    /**
     * 构建数据库通用异常
     * 错误码是自带的：DB_COMMON_EXCEPTION
     *
     * @param msg 提示参数
     * @param e   Throwable
     * @return 数据库通用异常
     */
    public static CbException dbException(String msg, Throwable e) {
        return CbExceptionFactory.exception(CbExceptionCodeEnum.DB_COMMON_EXCEPTION, e, msg);
    }

    /**
     * 构建RPC通用异常
     * 错误码是自带的：RPC_COMMON_EXCEPTION
     *
     * @param msg 提示参数
     * @return 数据库通用异常
     */
    public static CbException rpcException(String msg) {
        return CbExceptionFactory.exception(CbExceptionCodeEnum.RPC_COMMON_EXCEPTION, msg);
    }

    /**
     * 构建RPC通用异常
     * 错误码是自带的：RPC_COMMON_EXCEPTION
     *
     * @param msg 提示参数
     * @param e   Throwable
     * @return 数据库通用异常
     */
    public static CbException rpcException(String msg, Throwable e) {
        return CbExceptionFactory.exception(CbExceptionCodeEnum.RPC_COMMON_EXCEPTION, e, msg);
    }

}
