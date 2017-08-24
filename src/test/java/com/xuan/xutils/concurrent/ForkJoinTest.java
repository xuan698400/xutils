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
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }

        long start = System.currentTimeMillis();
        List<String> resultList = getResultList2(list);

        System.out.println("++++++++++time:" + (System.currentTimeMillis() - start));
        System.out.println("++++++++++result:" + resultList);
    }

    /**
     * 串行：耗时在450左右
     *
     * @param list
     * @return
     */
    private List<String> getResultList1(List<String> list) {
        List<String> resultList = new ArrayList<>();
        for (String str : list) {
            HttpResponse response = HttpUtils.get("http://www.baidu.com", null);
            resultList.add(response.getResultStr());
        }
        return resultList;
    }

    /**
     * 并发执行：耗时150左右
     *
     * @param list
     * @return
     */
    private List<String> getResultList2(List<String> list) {
        ListTaskExcutor<String, String> listTaskExcutor = TaskExecutorFactory.getListTaskExcutor();
        ListTaskResult<String> listTaskResult = listTaskExcutor.execute(list, new ListTaskCallable<String, String>() {
            @Override
            public String call(String str) {
                HttpResponse response = HttpUtils.get("http://www.baidu.com", null);
                return response.getResultStr();
            }
        });
        return listTaskResult.getList();
    }

}
