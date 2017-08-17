package com.xuan.xutils.concurrent.schedule;

/**
 * 描述某个系统任务的抽象基类
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:27:10 $
 */
public abstract class AbstractRunnableTask extends AbstractTask implements Runnable {

    public AbstractRunnableTask(String name) {
        super(name);
    }

    /**
     * 处理任务执行的方法.
     * 
     * @throws Exception
     *             执行异常时抛出
     */
    public abstract void processTask() throws Exception;

    /**
     * 任务线程执行的方法, 直接调用任务处理方法.
     */
    @Override
    public void run() {
        if (!isWorking) {
            return;
        }

        try {
            beforeProcessTask();

            long current = System.currentTimeMillis();
            processTask();
            long elapsed = System.currentTimeMillis() - current;
            afterProcessTask();

            System.out.println("Task[" + getName() + "] finished, elapsed " + elapsed + " ms");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
