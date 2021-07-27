package com.extp.framework.model;

/**
 * @author xuan
 * @since 2021/2/25
 */
public class BizInstance {
    public final static String DEFAULT_SCENARIO = "#DefaultScenario#";

    private String bizCode;

    private String scenario = DEFAULT_SCENARIO;

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }
}
