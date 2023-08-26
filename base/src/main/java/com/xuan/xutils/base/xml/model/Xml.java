package com.xuan.xutils.base.xml.model;

/**
 * XML
 *
 * @author xuan
 * @since 2020/11/10
 */
public class Xml {

    /**
     * XML协议
     */
    private XmlSchema schema;

    /**
     * XML协议根节点
     */
    private XmlNode rootNode;

    public XmlSchema getSchema() {
        return schema;
    }

    public void setSchema(XmlSchema schema) {
        this.schema = schema;
    }

    public XmlNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(XmlNode rootNode) {
        this.rootNode = rootNode;
    }

}
