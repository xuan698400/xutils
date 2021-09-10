//package com.xuan.mix.concurrent.batchtask.executor.forkjoin;
//
//import com.xuan.mix.concurrent.batchtask.BatchTaskExecutor;
//import com.xuan.mix.concurrent.batchtask.BatchTaskCallable;
//import com.xuan.mix.concurrent.batchtask.BatchTaskConfig;
//import com.xuan.mix.concurrent.batchtask.BatchTaskException;
//import com.xuan.mix.concurrent.batchtask.BatchTaskResult;
//
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ForkJoinPool;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;
//
///**
// * 以ForkJoin实现并发执行
// * <p>
// *
// * @author xuan
// * @date 17/8/29
// */
//public class BatchTaskExecutorForkJoinImpl<T, R> implements BatchTaskExecutor<T, R> {
//    /**
//     * ForkJoin线程池，一个执行器为一个线程池。
//     * 为了可控，一个应用最好使用Spring只实例化一个ListTaskExecutor实例，虽然性能上会打折扣，但是我们还是要保证资源不被耗尽而影响整个应用
//     */
//    private ForkJoinPool forkJoinPool;
//
//    /**
//     * 构造方法：可以线程池的数量
//     *
//     * @param parallelism 线程池并发数
//     */
//    public BatchTaskExecutorForkJoinImpl(int parallelism) {
//        forkJoinPool = new ForkJoinPool(parallelism);
//    }
//
//    /**
//     * 构造方法：默认线程池的数量为10
//     */
//    public BatchTaskExecutorForkJoinImpl() {
//        this(10);
//    }
//
//    @Override
//    public BatchTaskResult<R> execute(List<T> originList, BatchTaskCallable<T, R> callable, BatchTaskConfig config) {
//        ForkJoinTask<T, R> listTask = new ForkJoinTask<>(originList, callable, config);
//        Future<BatchTaskResult<R>> future = forkJoinPool.submit(listTask);
//        try {
//            return future.get(config.getTimeoutSecond(), TimeUnit.SECONDS);
//        } catch (InterruptedException | ExecutionException | TimeoutException e) {
//            BatchTaskResult<R> batchTaskResult = new BatchTaskResult<>();
//            batchTaskResult.setSuccess(false);
//            batchTaskResult.setResultMsg(e.getMessage());
//            batchTaskResult.setException(e);
//            return batchTaskResult;
//        }
//    }
//
//    @Override
//    public BatchTaskResult<R> execute(List<T> originList, BatchTaskCallable<T, R> callable, int subOriginListSize,
//        int timeout) {
//
//        BatchTaskConfig config = new BatchTaskConfig();
//        config.setSubOriginListSize(subOriginListSize);
//        config.setTimeoutSecond(timeout);
//        return execute(originList, callable, config);
//    }
//
//}
