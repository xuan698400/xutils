package com.xuan.xutils.concurrent.forkjoin.listtask.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 总任务返回结果
 * <p>
 * Created by xuan on 17/8/23.
 */
public class ListTaskResult<R> {

    /**
     * 合并后的结果列表
     */
    private List<R> list;

    /**
     * 把from的结果对象数据合并到this
     *
     * @param from
     * @return
     */
    public ListTaskResult<R> mergeFrom(ListTaskResult<R> from) {
        if (null == from || null == from.getList() || from.getList().size() == 0) {
            return this;
        }
        if (null == this.getList()) {
            this.setList(new ArrayList<>());
        }
        this.getList().addAll(from.getList());
        return this;
    }

    public List<R> getList() {
        return list;
    }

    public void setList(List<R> list) {
        this.list = list;
    }
}
