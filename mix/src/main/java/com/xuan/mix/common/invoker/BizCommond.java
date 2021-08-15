package com.xuan.mix.common.invoker;

/**
 * 把一次调用包装成一个命令，提交给BizInvoker执行
 *
 * @author xuan
 * @since 2021/1/21
 */
public interface BizCommond<T> {

    BizCommondResult<T> call();
}
