package com.xuan.mix.concurrent.batchtask.test;

import java.util.ArrayList;
import java.util.List;

import com.xuan.mix.concurrent.batchtask.AbstractSingleSizeBatchTaskCallable;
import com.xuan.mix.concurrent.batchtask.BatchSubTaskResult;
import com.xuan.mix.concurrent.batchtask.BatchTaskExecutor;
import com.xuan.mix.concurrent.batchtask.BatchTaskExecutorFactory;
import com.xuan.mix.concurrent.batchtask.BatchTaskResult;

/**
 * @author xuan
 * @since 2021/8/16
 */
public class Main {

    private static BatchTaskExecutor<OriginClass, TargetClass> listTaskExecutor;

    static {
        listTaskExecutor = BatchTaskExecutorFactory.getParallelExecutor();
    }

    public static void main(String[] args) {

        List<OriginClass> originList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            OriginClass oc = new OriginClass();
            oc.setName(i + "");
            originList.add(oc);
        }

        long start = System.currentTimeMillis();
        BatchTaskResult<TargetClass> targetList = listTaskExecutor.execute(originList,
            new AbstractSingleSizeBatchTaskCallable<OriginClass, TargetClass>() {
                @Override
                protected void doSingleCall(OriginClass originClass,
                    BatchSubTaskResult<TargetClass> subTaskResult) {
                    sleep();
                    TargetClass tc = new TargetClass();
                    tc.setName(originClass.getName() + "_加工");

                    if (null == subTaskResult.getList()) {
                        subTaskResult.setList(new ArrayList<>());
                    }
                    subTaskResult.setSuccess(true);
                    subTaskResult.getList().add(tc);

                    System.out.println("加工完成" + originClass.getName());
                }
            }, 1, 5000);
        System.out.println(targetList);
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
