package com.xuan.xutils.concurrent.schedule;

import java.util.concurrent.Callable;

/**
 * 描述某个系统任务的抽象基类.和 <code>AbstractRunnableTask</code> 的区别是此任务带有执行的结果
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:29:22 $
 */
public abstract class AbstractCallableTask<V> extends AbstractTask implements Callable<V> {

    public AbstractCallableTask(String name) {
        super(name);
    }

    /**
     * 处理任务执行的方法.
     * 
     * @return 任务执行结果
     * @throws Exception
     *             执行异常时抛出
     */
    public abstract V processTask() throws Exception;

    /**
     * 任务线程执行的方法, 直接调用任务处理方法.
     */
    @Override
    public V call() {
        if (!isWorking) {
            return null;
        }

        V result = null;

        try {
            beforeProcessTask();

            long current = System.currentTimeMillis();
            result = processTask();
            long elapsed = System.currentTimeMillis() - current;

            afterProcessTask();

            System.out.println("Task[" + getName() + "] finished, elapsed " + elapsed + " ms");
        }
        catch (Exception e) {
            e.printStackTrace();;
        }

        return result;
    }

    /**
     * 任务执行前操作,子类根据自己要求可复写
     */
    protected void beforeProcessTask(){
    }

    /**
     * 任务执行后操作,子类根据自己要求可复写
     */
    protected void afterProcessTask(){
    }

}
