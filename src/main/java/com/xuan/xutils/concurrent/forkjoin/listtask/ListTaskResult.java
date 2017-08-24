package com.xuan.xutils.concurrent.forkjoin.listtask;

import java.util.ArrayList;
import java.util.List;

/**
 * 总任务返回结果
 * <p>
 * Created by xuan on 17/8/23.
 */
public class ListTaskResult<R> {

    /**
     * 中间小任务处理接口临时存放
     */
    private R r;

    /**
     * 合并的结果
     */
    private List<R> list = new ArrayList<>();

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public List<R> getList() {
        return list;
    }

    public void setList(List<R> list) {
        this.list = list;
    }
    
}
