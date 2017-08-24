package com.xuan.xutils.concurrent.forkjoin;

import com.xuan.xutils.concurrent.forkjoin.listtask.ListTaskExcutor;

/**
 * 任务执行器工厂类
 * <p>
 * Created by xuan on 17/8/23.
 */
public class TaskExecutorFactory {

    /**
     * 获取ListTask执行器
     *
     * @return
     */
    public static <T, R> ListTaskExcutor<T, R> getListTaskExcutor() {
        return new ListTaskExcutor<>();
    }

}
