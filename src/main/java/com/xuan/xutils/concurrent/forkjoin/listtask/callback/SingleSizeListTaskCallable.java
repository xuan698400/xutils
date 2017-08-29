package com.xuan.xutils.concurrent.forkjoin.listtask.callback;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果用户想更方便，只需要对原始数据单个单个处理，可以继承这个去实现。
 * 如果子任务拥有多个原始数据需要处理，这里会for循环进行串行处理
 * <p>
 * Created by xuan on 17/8/26.
 */
public abstract class SingleSizeListTaskCallable<T, R> extends AbstractListTaskCallable<T, R> {

    @Override
    public List<R> call(List<T> list) {
        List<R> rList = new ArrayList<>();
        for (T t : list) {
            rList.add(call(t));
        }
        return rList;
    }

    /**
     * 子类实现，处理单个原始数据
     *
     * @param t
     * @return
     */
    protected abstract R call(T t);

}
