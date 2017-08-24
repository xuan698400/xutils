package com.xuan.xutils.concurrent;

import com.xuan.xutils.concurrent.forkjoin.TaskExecutorFactory;
import com.xuan.xutils.concurrent.forkjoin.listtask.ListTaskCallable;
import com.xuan.xutils.concurrent.forkjoin.listtask.ListTaskExcutor;
import com.xuan.xutils.concurrent.forkjoin.listtask.ListTaskResult;
import com.xuan.xutils.http.HttpResponse;
import com.xuan.xutils.http.HttpUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuan on 17/8/24.
 */
public class ForkJoinTest {

    @Test
    public void testListTask() {
        run1();
        run2();
    }

    /**
     * 串行：耗时在450左右
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
     * 并发执行：耗时150左右
     *
     * @return
     */
    private void run2() {
        List<String> list = initList();
        //
        long start = System.currentTimeMillis();
        ListTaskExcutor<String, String> listTaskExcutor = TaskExecutorFactory.getListTaskExcutor();
        ListTaskResult<String> listTaskResult = listTaskExcutor.execute(list, new ListTaskCallable<String, String>() {
            @Override
            public String call(String str) {
                return doThing(str);
            }
        });
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
        for (int i = 0; i < 20; i++) {
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
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
