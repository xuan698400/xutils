package com.xuan.moho.xml.model;

/**
 * XML协议头，例如：
 * <?xml version="1.0" encoding="UTF-8"?>
 *
 * @author xuan
 * @since 2020/11/10
 */
public class XmlSchemaModel {

    private String version;

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
