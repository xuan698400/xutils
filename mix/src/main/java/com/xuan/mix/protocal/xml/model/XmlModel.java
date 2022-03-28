package com.xuan.mix.protocal.xml.model;

/**
 * @author xuan
 * @since 2020/11/10
 */
public class XmlModel {

    /**
     * 例如：<?xml version="1.0" encoding="UTF-8"?>
     */
    private XmlSchemaModel schemaModel;

    private XmlNodeModel nodeModel;

    public XmlSchemaModel getSchemaModel() {
        return schemaModel;
    }

    public void setSchemaModel(XmlSchemaModel schemaModel) {
        this.schemaModel = schemaModel;
    }

    public XmlNodeModel getNodeModel() {
        return nodeModel;
    }

    public void setNodeModel(XmlNodeModel nodeModel) {
        this.nodeModel = nodeModel;
    }

}
