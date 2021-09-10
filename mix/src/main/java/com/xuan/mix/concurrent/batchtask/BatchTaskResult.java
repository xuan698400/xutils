package com.xuan.mix.concurrent.batchtask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 任务执行结果对象
 * 注意：子任务执行成功后生成的结果对象也会使用他，最后结果汇总也使用的他
 * <p>
 *
 * @author xuan
 * @date 17/8/29
 */
public class BatchTaskResult<R> {

    /**
     * 整个任务失败时会设置成false
     */
    private boolean success = true;
    /**
     * 失败时会提示错误信息
     */
    private String resultMsg;
    /**
     * 所有子任务处理结果
     */
    private List<BatchSubTaskResult<R>> subTaskResultList;
    /**
     * 如果存在
     */
    private Exception exception;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public List<BatchSubTaskResult<R>> getSubTaskResultList() {
        return subTaskResultList;
    }

    public void setSubTaskResultList(List<BatchSubTaskResult<R>> subTaskResultList) {
        this.subTaskResultList = subTaskResultList;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public List<R> getSuccessResultDataList() {
        List<R> resultDataList = new ArrayList<>();

        if (null == subTaskResultList || subTaskResultList.isEmpty()) {
            return resultDataList;
        }

        for (BatchSubTaskResult<R> subTaskResult : subTaskResultList) {
            if (null == subTaskResult || !subTaskResult.isSuccess() || null == subTaskResult.getList()) {
                continue;
            }
            resultDataList.addAll(subTaskResult.getList());
        }
        return resultDataList;
    }

    /**
     * 把from的结果对象数据合并到this
     *
     * @param from
     * @return to
     */
    public BatchTaskResult<R> mergeFrom(BatchTaskResult<R> from) {
        if (null == from || null == from.getSubTaskResultList() || from.getSubTaskResultList().size() == 0) {
            return this;
        }
        if (null == this.getSubTaskResultList()) {
            this.setSubTaskResultList(new CopyOnWriteArrayList<>());
        }
        this.getSubTaskResultList().addAll(from.getSubTaskResultList());
        return this;
    }

}
