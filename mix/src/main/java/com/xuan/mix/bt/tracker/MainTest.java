package com.xuan.mix.bt.tracker;

/**
 * @author xuan
 * @since 2020/11/20
 */
public class MainTest {

    public static void main(String args[]) {
        //建议配置到配置中心
        TkConfig config = getConfig();

        //在调用开始前start
        Tracker.start("[Tracker-main-start]", "start", config);

        //中间任何地方都可以记录日志打点
        Tracker.on("[Tracker-main-1]", "1");
        sleep(100);
        Tracker.on("[Tracker-main-2]", "2");
        sleep(450);

        Tracker.on("[Tracker-main-3]", "3");

        //等调用结束后end,进行数据分析处理
        Tracker.end("[Tracker-main-end]", "end", new EndCallback() {
            @Override
            public void apply(TkPointLine line) {
                System.out.println(line.getPointList().toString());
            }
        });
    }

    private static TkConfig getConfig() {
        TkConfig config = new TkConfig();
        config.setTrace(true);
        config.setLogGreaterInterval(500L);
        return config;
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            //Ignore
        }
    }

}
