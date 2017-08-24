package com.xuan.xutils.concurrent.forkjoin.listtask.bak;

import java.util.ArrayList;
import java.util.List;

/**
 * 总任务返回结果
 * <p>
 * Created by xuan on 17/8/23.
 */
public class ListTaskResult<T, R> {

    /**
     * 只要有一个子任务操作失败，seccess=false
     */
    private boolean success = true;

    /**
     * 子任务成功数
     */
    private int successCount = 0;

    /**
     * 子任务失败数
     */
    private int failCount = 0;

    /**
     * 结果列表
     */
    private List<R> list = new ArrayList<>();

    /**
     * 添加处理结果
     *
     * @param tList
     * @param rList
     */
    public void appendResult(List<T> tList, List<R> rList) {
        if (null == rList && rList.size() == 0) {
            this.setSuccess(false);
            this.setFailCount(tList.size());
            this.setSuccessCount(0);
        } else {
            this.setSuccess(tList.size() == rList.size());
            this.setFailCount(rList.size() - tList.size());
            this.setSuccessCount(rList.size());
            this.getList().addAll(rList);
        }
    }

    /**
     * 合并结果
     *
     * @param from
     * @return
     */
    public ListTaskResult<T, R> merge(ListTaskResult<T, R> from) {
        if (null == from) {
            return this;
        }
        this.setSuccess(this.isSuccess() && from.isSuccess());
        this.setFailCount(this.getFailCount() + from.getFailCount());
        this.setSuccessCount(this.getSuccessCount() + from.getSuccessCount());
        this.getList().addAll(from.getList());
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public List<R> getList() {
        return list;
    }

    public void setList(List<R> list) {
        this.list = list;
    }

}
