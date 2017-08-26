package com.xuan.xutils.concurrent.forkjoin.listtask.callback;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果用户想更方便的，只需要对原始数据单个单个处理，可以继承这个
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
     * 子类实现
     *
     * @param t
     * @return
     */
    protected abstract R call(T t);

}
