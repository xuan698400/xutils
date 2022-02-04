package com.xuan.lock.common;

/**
 * @author xuan
 * @since 2022/1/27
 */
public class DbLockModel {
    /**
     * 锁名称
     */
    private String name;
    /**
     * 锁的值，一般设置程当前机器以及持有线程名称
     */
    private String value;
    /**
     * 锁的创建时间
     */
    private Long createTime;
    /**
     * 锁超时时间，单位：毫秒
     */
    private Integer timeout;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

}
