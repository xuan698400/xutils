package com.xuan.common.invoker;

import com.xuan.common.model.BizResult;

/**
 * 把一次调用包装成一个命令，提交给BizInvoker执行
 *
 * @author xuan
 * @since 2021/1/21
 */
public interface BizCommond<T> {

    BizResult<T> call();
}
