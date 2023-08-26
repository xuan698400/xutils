package com.xuan.xutils.base.exception;

/**
 * 异常码接口规范，系统定义异常码枚举，可以实现该接口对异常码进行规范
 * 这里支持二级异常码：
 * 第一级可以设置成异常大类，例如：网络异常、数据库异常、RPC调用异常、业务异常等
 * 第二级可以设置成具体大类下的二级异常码，例如，参数为空等
 *
 * @author xuan
 * @since 2020/2/28
 */
public interface BizExceptionCode {

    /**
     * 返回一级异常码
     *
     * @return 一级异常码
     */
    String getCode();

    /**
     * 返回二级异常码
     *
     * @return 二级异常码
     */
    String getSubCode();

    /**
     * 提示信息，枚举定义提示信息时只是String的format格式，例如：错误异常:%s
     * 这里params参数就是替换%s的
     *
     * @param params 参数
     * @return 返回String.format后的错误信息
     */
    String getMsg(Object... params);
}
