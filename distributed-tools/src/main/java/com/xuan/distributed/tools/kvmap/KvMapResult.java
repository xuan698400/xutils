package com.xuan.distributed.tools.kvmap;

/**
 * @author xuan
 * @since 2022/9/10
 */
public class KvMapResult {

    private boolean success;

    private String value;

    private long version;

    private boolean expire;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public boolean isExpire() {
        return expire;
    }

    public void setExpire(boolean expire) {
        this.expire = expire;
    }

}
