package com.xuan.moho.xml.model;

/**
 * @author xuan
 * @since 2020/11/10
 */
public class XmlModel {

    private XmlSchemaModel schema;

    private XmlNodeModel rootNode;

    public XmlSchemaModel getSchema() {
        return schema;
    }

    public void setSchema(XmlSchemaModel schema) {
        this.schema = schema;
    }

    public XmlNodeModel getRootNode() {
        return rootNode;
    }

    public void setRootNode(XmlNodeModel rootNode) {
        this.rootNode = rootNode;
    }
    
}
