package com.xuan.mix.bt.tracker;

/**
 * 很吊的一个跟踪器,目前用来跟踪长RT的原因
 *
 * @author xuan
 * @since 2020/11/20
 */
public class Tracker {

    /**
     * 每个线程都有一个ThreadLocalMap, 而一个ThreadLocal实例就是一个key值
     */
    private final static ThreadLocal<TkPointLine> TRACE_LINE = new ThreadLocal<>();
    /**
     * 第一次start时，设置，后面往下传就行
     */
    private final static ThreadLocal<TkConfig> CONFIG = new ThreadLocal<>();

    /**
     * 打点开始,name规范,可以这样,[类名-方法名-方法内部逻辑编号]
     *
     * @param name   节点名称
     * @param remark 节点备注
     * @param config 配置
     */
    public static void start(String name, String remark, TkConfig config) {
        if (notTrace(config)) {
            return;
        }

        //设置一个链路,并初始化第一个日志点
        TkPointLine line = new TkPointLine();
        line.addPoint(TkPointBuilder.buildStart(name, remark));
        TRACE_LINE.set(line);
        CONFIG.set(config);
    }

    /**
     * 打点,name规范,可以这样,[类名-方法名-方法内部逻辑编号]
     *
     * @param name   节点名称
     * @param remark 节点备注
     */
    public static void on(String name, String remark) {
        TkConfig config = CONFIG.get();

        if (notTrace(config)) {
            return;
        }

        //到这里肯定有调用了start，所以里面肯定有至少一个节点了
        TkPointLine line = TRACE_LINE.get();
        int size = line.getPointList().size();
        TkPoint on = TkPointBuilder.buildOn(line.getPointList().get(size - 1), name, remark);
        line.addPoint(on);
    }

    /**
     * 打点结束,name规范,可以这样,[类名-方法名-方法内部逻辑编号]
     *
     * @param name   节点名称
     * @param remark 节点备注
     */
    public static void end(String name, String remark, EndCallback endCallback) {
        TkConfig config = CONFIG.get();

        if (notTrace(config)) {
            return;
        }

        //到这里肯定至少有1个节点
        Tracker.on(name, remark);

        //打印超长耗时
        TkPointLine line = TRACE_LINE.get();
        int size = line.getPointList().size();
        Long totalInterval = line.getPointList().get(size - 1).getTimestamp() - line.getPointList()
            .get(0).getTimestamp();
        if (null != config.getLogGreaterInterval() && totalInterval - config.getLogGreaterInterval() > 0) {
            //最后多加一个点,记录总耗时
            Tracker.on("总耗时间", String.valueOf(totalInterval));
            if (null != endCallback) {
                endCallback.apply(line);
            }
        }

        //清理
        clear();
    }

    /**
     * 线程变量中的日志数据
     */
    public static void clear() {
        TRACE_LINE.remove();
        CONFIG.remove();
    }

    private static boolean notTrace(TkConfig config) {
        return null == config || null == config.getTrace() || !config.getTrace();
    }

}
