package com.xuan.xutils.concurrent;

import com.xuan.xutils.concurrent.forkjoin.TaskExecutorFactory;
import com.xuan.xutils.concurrent.forkjoin.listtask.ListTaskExcutor;
import com.xuan.xutils.concurrent.forkjoin.listtask.ListTaskResult;
import com.xuan.xutils.concurrent.forkjoin.listtask.SingleSizeListTaskCallable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 单任务执行时间：20MS
 * 总任务数：200
 * [4617,394]  [4612,327]  [4645,359]  [4553,334]
 * [4600,290]  [4609,389]  [4621,353]  [4600,382]
 * [4591,284]  [4604,332]  [4579,350]  [4603,377]
 * [4600,524]  [4609,425]  [4621,510]  [4600,522]
 * <p>
 * Created by xuan on 17/8/24.
 */
public class ForkJoinTest {

    @Test
    public void testListTask() {
        run1();
        run2();
    }

    /**
     * 串行
     *
     * @return
     */
    private void run1() {
        List<String> list = initList();
        //
        long start = System.currentTimeMillis();
        List<String> resultList = new ArrayList<>();
        for (String str : list) {
            resultList.add(doThing(str));
        }
        //
        System.out.println("++++++++++run1-time:" + (System.currentTimeMillis() - start));
        System.out.println("++++++++++run1-result:" + resultList);
    }

    /**
     * 并行
     *
     * @return
     */
    private void run2() {
        List<String> list = initList();
        //
        long start = System.currentTimeMillis();
        ListTaskExcutor<String, String> excutor = TaskExecutorFactory.getListTaskExcutor();
        ListTaskResult<String> listTaskResult = excutor.execute(list, new SingleSizeListTaskCallable<String, String>() {
            @Override
            protected String call(String s) {
                return doThing(s);
            }
        }, 1);
        //
        System.out.println("++++++++++run2-time:" + (System.currentTimeMillis() - start));
        System.out.println("++++++++++run2-result:" + listTaskResult.getList());
    }

    /**
     * 初始化原始数据
     *
     * @return
     */
    private List<String> initList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    /**
     * 实际一个小任务要做的事情
     *
     * @param str
     * @return
     */
    private String doThing(String str) {
        sleep();
        return str + "_deal";
    }

    private void sleep() {
        try {
            Thread.sleep(20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
