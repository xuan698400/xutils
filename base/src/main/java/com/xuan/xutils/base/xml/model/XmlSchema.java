package com.xuan.xutils.base.xml.model;

/**
 * XML协议，例如：
 * <?xml version="1.0" encoding="UTF-8"?>
 *
 * @author xuan
 * @since 2020/11/10
 */
public class XmlSchema {

    /**
     * 协议版本
     */
    private String version;

    /**
     * 协议编码
     */
    private String encoding;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

}
