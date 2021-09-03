package com.xuan.mix.bt.nodelog.config;

/**
 * @author xuan
 * @since 2019/10/31
 */
public class NodeLogConfig {
    /**
     * sample为万分比
     */
    public final static int SAMPLE_DENOMINATOR = 10000;

    /**
     * 日志采样率，单位：万分之几
     */
    private int sample = 10000;
    /**
     * 是否输出入参
     */
    private boolean printParams = true;
    /**
     * 是否输出返回结果
     */
    private boolean printResult = true;
    /**
     * 如果结果失败，是否输出所有信息，设置成true时，错误case一定会被记录，会忽略sample
     */
    private Boolean printIfResultFail = true;

    public int getSample() {
        return sample;
    }

    public void setSample(int sample) {
        this.sample = sample;
    }

    public boolean isPrintParams() {
        return printParams;
    }

    public void setPrintParams(boolean printParams) {
        this.printParams = printParams;
    }

    public boolean isPrintResult() {
        return printResult;
    }

    public void setPrintResult(boolean printResult) {
        this.printResult = printResult;
    }

    public Boolean getPrintIfResultFail() {
        return printIfResultFail;
    }

    public void setPrintIfResultFail(Boolean printIfResultFail) {
        this.printIfResultFail = printIfResultFail;
    }

}
