package com.xuan.spring.utils.nodelog.config;

/**
 * @author xuan
 * @since 2019/10/31
 */
public class NodeLogConfig {

    public final static String NODE_NAME_DEFAULT = "default";
    private final static Boolean PRINT_PARAMS_DEFAULT = false;
    private final static Boolean PRINT_RESULT_DEFAULT = false;
    private final static Boolean PRINT_STACK_DEFAULT = false;
    private final static Integer SMAPLE_DEFAULT = 0;
    private final static Boolean PRINT_IF_RESULT_FAIL_DEFAULT = false;

    public final static NodeLogConfig DEFAULT = new NodeLogConfig();

    static {
        DEFAULT.setPrintParams(PRINT_PARAMS_DEFAULT);
        DEFAULT.setPrintResult(PRINT_RESULT_DEFAULT);
        DEFAULT.setPrintStack(PRINT_STACK_DEFAULT);
        DEFAULT.setSample(SMAPLE_DEFAULT);
        DEFAULT.setPrintIfResultFail(PRINT_IF_RESULT_FAIL_DEFAULT);
    }

    /**
     * 日志采样率，单位：万分之几
     */
    private Integer sample;

    /**
     * 是否输出入参
     */
    private Boolean printParams;

    /**
     * 是否输出返回结果
     */
    private Boolean printResult;

    /**
     * 是否输出返回结果
     */
    private Boolean printStack;

    /**
     * 如果结果失败，是否输出所有信息，设置成true时，错误case一定会被记录
     */
    private Boolean printIfResultFail;

    private String nodeName;

    public Integer getSample() {
        if (null == sample) {
            return NodeLogConfig.SMAPLE_DEFAULT;
        }

        return sample;
    }

    public void setSample(Integer sample) {
        this.sample = sample;
    }

    public Boolean getPrintParams() {
        if (null == printParams) {
            return NodeLogConfig.PRINT_PARAMS_DEFAULT;
        }

        return printParams;
    }

    public void setPrintParams(Boolean printParams) {
        this.printParams = printParams;
    }

    public Boolean getPrintResult() {
        if (null == printResult) {
            return NodeLogConfig.PRINT_RESULT_DEFAULT;
        }

        return printResult;
    }

    public void setPrintResult(Boolean printResult) {
        this.printResult = printResult;
    }

    public Boolean getPrintStack() {
        if (null == printStack) {
            return NodeLogConfig.PRINT_STACK_DEFAULT;
        }

        return printStack;
    }

    public void setPrintStack(Boolean printStack) {
        this.printStack = printStack;
    }

    public Boolean getPrintIfResultFail() {
        if (null == printIfResultFail) {
            return NodeLogConfig.PRINT_IF_RESULT_FAIL_DEFAULT;
        }

        return printIfResultFail;
    }

    public void setPrintIfResultFail(Boolean printIfResultFail) {
        this.printIfResultFail = printIfResultFail;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

}
