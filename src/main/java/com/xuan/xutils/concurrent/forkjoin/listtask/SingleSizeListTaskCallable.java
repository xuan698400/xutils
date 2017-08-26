package com.xuan.xutils.concurrent.forkjoin.listtask;

import java.util.ArrayList;
import java.util.List;

/**
 * 单个数据单个任务处理，方便用户使用
 * <p>
 * Created by xuan on 17/8/26.
 */
public abstract class SingleSizeListTaskCallable<T, R> implements ListTaskCallable<T, R> {

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
