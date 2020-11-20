package com.xuan.mix.bt.tracker;

/**
 * 配置，可以考虑放到配置中心来动态推动是否需要跟踪
 *
 * @author xuan
 * @since 2020/11/20
 */
public class TkConfig {

    /**
     * 是否开启记录
     */
    private Boolean trace = true;
    /**
     * 整个链路超过一定时间，进行LOG记录，单位：毫秒
     */
    private Long logGreaterInterval = 500L;

    public Boolean getTrace() {
        return trace;
    }

    public void setTrace(Boolean trace) {
        this.trace = trace;
    }

    public Long getLogGreaterInterval() {
        return logGreaterInterval;
    }

    public void setLogGreaterInterval(Long logGreaterInterval) {
        this.logGreaterInterval = logGreaterInterval;
    }

}
