//package com.xuan.mix.concurrent.batchtask.executor.forkjoin;
//
//import com.xuan.mix.concurrent.batchtask.BatchSubTaskResult;
//import com.xuan.mix.concurrent.batchtask.BatchTaskCallable;
//import com.xuan.mix.concurrent.batchtask.BatchTaskConfig;
//import com.xuan.mix.concurrent.batchtask.BatchTaskResult;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.RecursiveTask;
//
///**
// * 任务核心代码，这个任务主要做两件事
// * 第一：如果原数据足够多，就会进行任务拆分，拆分到原数据足够小的子任务
// * 第二：如果原数据足够少，调用用户传入的逻辑回调拿到结果数据
// * <p>
// *
// * @author xuan
// * @date 17/8/29
// */
//public class ForkJoinTask<T, R> extends RecursiveTask<BatchTaskResult<R>> {
//    private static final long serialVersionUID = 1;
//
//    /**
//     * 配置参数
//     */
//    private BatchTaskConfig config;
//
//    /**
//     * 任务需要处理的原始数据
//     */
//    private List<T> originList;
//
//    /**
//     * 用户逻辑回调
//     */
//    private BatchTaskCallable<T, R> callable;
//
//    /**
//     * 构造函数
//     *
//     * @param originList 原数据
//     * @param callable   逻辑处理回调
//     * @param config     配置参数
//     */
//    public ForkJoinTask(List<T> originList, BatchTaskCallable<T, R> callable, BatchTaskConfig config) {
//        if (null == originList || originList.size() == 0) {
//            throw new RuntimeException("[ForkJoinTask-ForkJoinTask] originList is empty.");
//        }
//        if (null == callable) {
//            throw new RuntimeException("[ForkJoinTask-ForkJoinTask] callable is null.");
//        }
//        if (null == config) {
//            throw new RuntimeException("[ForkJoinTask-ForkJoinTask] config is null.");
//        }
//        this.originList = originList;
//        this.callable = callable;
//        this.config = config;
//    }
//
//    @Override
//    protected BatchTaskResult<R> compute() {
//        if (originList.size() <= config.getSubOriginListSize()) {
//            //如果原数据足够少，即小于配置参数指定的单个任务的原数据数量时，进行用户的业务逻辑回调执行，拿到结果
//            List<BatchSubTaskResult<R>> resultList = callable.call(originList);
//            BatchTaskResult<R> result = new BatchTaskResult<>();
//            result.setSubTaskResultList(resultList);
//            return result;
//        } else {
//            //如果原数据足够多，那么需要进行任务拆分，根据配置，拆分成多个子任务
//            //例如：原数据=20个，用户配置子任务的原数据数量subOriginListSize=3
//            //那么：会拆分任务：3，3，3，3，3，3，2一共7个子任务
//            List<ForkJoinTask<T, R>> taskList = new ArrayList<>();
//            int subTaskSize = originList.size() / config.getSubOriginListSize();
//            if (originList.size() % config.getSubOriginListSize() > 0) {
//                //未除尽有余数，需要额外再加一个
//                subTaskSize += 1;
//            }
//            for (int i = 0; i < subTaskSize; i++) {
//                int start = i * config.getSubOriginListSize();
//                int end = i * config.getSubOriginListSize() + config.getSubOriginListSize();
//                if (end > originList.size()) {
//                    //最后一个任务，未必刚好是subOriginListSize的数量，所以end值进行调整
//                    end = originList.size();
//                }
//                List<T> subList = originList.subList(start, end);
//                ForkJoinTask<T, R> subTask = new ForkJoinTask<>(subList, callable, config);
//                subTask.fork();
//                taskList.add(subTask);
//            }
//            //合并任务执行结果
//            BatchTaskResult<R> result = new BatchTaskResult<>();
//            for (ForkJoinTask<T, R> task : taskList) {
//                result.mergeFrom(task.join());
//            }
//            return result;
//        }
//    }
//
//}
