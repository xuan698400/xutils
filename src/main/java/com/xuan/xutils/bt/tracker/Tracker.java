package com.xuan.xutils.bt.tracker;


/**
 * 很吊的一个跟踪器,目前用来跟踪长RT的原因
 * <p>
 * Created by xuan on 17/7/29.
 */
public abstract class Tracker {

    /**
     * 在打点结束时,如果超过这个时间,需要日志输出,考虑放到配置中心
     */
    private static final Long DEFAULT_LOG_INTERVAL = 500L;

    /**
     * 是否开启记录,考虑放到配置中心
     */
    private static final boolean TRACE = true;

    //private static final Logger log = LoggerFactory.getLogger(Tracker.class);

    /**
     * 每个线程都有一个ThreadLocalMap, 而一个ThreadLocal实例就是一个key值
     */
    private static ThreadLocal<TKPointLine> traceLine = new ThreadLocal<>();

    /**
     * 打点开始,name规范,可以这样,[类名-方法名-方法内部逻辑编号]
     *
     * @param name
     */
    public static void start(String name) {
        if (!TRACE) {
            return;
        }

        if (null == name || name.length() == 0) {
            return;
        }

        //设置一个链路,并初始化第一个日志点
        TKPointLine tkPointLine = new TKPointLine();
        tkPointLine.addPoint(TKPoint.buildStart(name));
        traceLine.set(tkPointLine);
    }

    /**
     * 打点,name规范,可以这样,[类名-方法名-方法内部逻辑编号]
     *
     * @param name
     * @param remark
     */
    public static void on(String name, String remark) {
        if (!TRACE) {
            return;
        }

        if (null == name || name.length() == 0) {
            return;
        }

        TKPointLine tkPointLine = getTraceLine();
        if (null == tkPointLine) {
            return;
        }

        int size = tkPointLine.getPointList().size();
        if (0 == size) {
            tkPointLine.addPoint(TKPoint.buildStart(name));
        } else {
            TKPoint on = TKPoint.buildOn(tkPointLine.getPointList().get(size - 1), name);
            //设置备注可以更好的分析日志
            if (null == remark || remark.length() == 0) {
                on.setRemark(remark);
            }
            tkPointLine.addPoint(on);
        }
    }

    /**
     * 打点结束,name规范,可以这样,[类名-方法名-方法内部逻辑编号]
     *
     * @param name
     */
    public static void end(String name) {
        if (!TRACE) {
            return;
        }

        Tracker.on(name, null);

        TKPointLine tkPointLine = getTraceLine();
        if (null == tkPointLine) {
            return;
        }

        int size = tkPointLine.getPointList().size();
        if (size < 3) {
            //如果只有两个点,说明只有开始和结束,防止别的接口污染日志,所以先不记录,只记录打了on的
            return;
        }

        //打印超长耗时
        Long interval = tkPointLine.getPointList().get(size - 1).getTimestamp().longValue() - tkPointLine.getPointList().get(0).getTimestamp().longValue();
        if (interval - DEFAULT_LOG_INTERVAL > 0) {
            Tracker.on("总耗时间", String.valueOf(interval));//多加一个点,记录总耗时
            //这里可以输入日志,或者上报到日志平台
            //log.warn("Tracker OPS:" + JSON.toJSONString(tkPointLine.getPointList()));
            System.out.println("Tracker OPS: " + tkPointLine.getPointList().toString());
        }
        //清理
        clear();
    }

    private static TKPointLine getTraceLine() {
        Object obj = traceLine.get();
        if (null == obj) {
            return null;
        }
        return (TKPointLine) obj;
    }

    /**
     * 线程变量中的日志数据
     */
    public static void clear() {
        Object obj = traceLine.get();
        if (null != obj) {
            traceLine.remove();
        }
    }

    public static void main(String args[]) {
        try {
            /**
             * 在调用开始前start
             */
            Tracker.start("[Tracker-main-start]");

            /**
             * 中间任何地方都可以记录日志打点
             */
            Tracker.on("[Tracker-main-1]", "1");
            Thread.sleep(100);
            Tracker.on("[Tracker-main-2]", "2");
            Thread.sleep(450);
            Tracker.on("[Tracker-main-3]", "3");

            /**
             * 等调用结束后end,进行数据分析处理
             */
            Tracker.end("[Tracker-main-end]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
