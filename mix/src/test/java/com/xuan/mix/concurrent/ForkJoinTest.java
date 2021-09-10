//package com.xuan.mix.concurrent;
//
//import com.xuan.mix.concurrent.batchtask.BatchTaskExecutorFactory;
//import com.xuan.mix.concurrent.batchtask.AbstractSingleSizeBatchTaskCallable;
//import com.xuan.mix.concurrent.batchtask.BatchTaskException;
//import com.xuan.mix.concurrent.batchtask.BatchTaskExecutor;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 需求描述：我有一定数量的字符串列表，现在要给这些字符串加上"_deal".
// * <p>
// * Created by xuan on 17/8/24.
// */
//public class ForkJoinTest {
//
//    BatchTaskExecutor executor = BatchTaskExecutorFactory.getExecutor();
//
//    @Test
//    public void testListTask() throws Throwable {
//        run1();
//        run2();
//        run3();
//    }
//
//    /**
//     * 串行
//     *
//     * @return
//     */
//    private void run1() {
//        List<String> list = initList();
//        //
//        long start = System.currentTimeMillis();
//        List<String> resultList = new ArrayList<>();
//        for (String str : list) {
//            resultList.add(doThing(str));
//        }
//        //
//        System.out.println("++++++++++run1-time:" + (System.currentTimeMillis() - start));
//        System.out.println("++++++++++run1-result:" + resultList);
//    }
//
//    /**
//     * 并行
//     *
//     * @return
//     */
//    private void run2() throws BatchTaskException {
//        //List<String> list = initList();
//        ////
//        //long start = System.currentTimeMillis();
//        //List<String> resultList = executor.execute(list, new AbstractSingleSizeBatchTaskCallable() {
//        //    @Override
//        //    protected String call(String s) {
//        //        return doThing(s);
//        //    }
//        //});
//        ////
//        //System.out.println("++++++++++run2-time:" + (System.currentTimeMillis() - start));
//        //System.out.println("++++++++++run2-result:" + resultList);
//    }
//
//    /**
//     * 线程池
//     */
//    private void run3() throws BatchTaskException {
//        //List<String> list = initList();
//        ////
//        //long start = System.currentTimeMillis();
//        //BatchTaskExecutor<String, String> executor = BatchTaskExecutorFactory.getCyclicBarrierExecutor();
//        //List<String> resultList = executor.execute(list, new AbstractSingleSizeBatchTaskCallable<String, String>() {
//        //    @Override
//        //    protected String call(String s) {
//        //        return doThing(s);
//        //    }
//        //});
//        ////
//        //System.out.println("++++++++++run3-time:" + (System.currentTimeMillis() - start));
//        //System.out.println("++++++++++run3-result:" + resultList);
//    }
//
//    /**
//     * 初始化原始数据
//     *
//     * @return
//     */
//    private List<String> initList() {
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 200; i++) {
//            list.add(String.valueOf(i));
//        }
//        return list;
//    }
//
//    /**
//     * 实际一个小任务要做的事情
//     *
//     * @param str
//     * @return
//     */
//    private String doThing(String str) {
//        sleep();
//        return str + "_deal";
//    }
//
//    private void sleep() {
//        try {
//            Thread.sleep(20);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}
