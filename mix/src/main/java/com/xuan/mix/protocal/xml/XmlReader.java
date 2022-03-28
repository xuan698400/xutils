package com.xuan.mix.protocal.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.xuan.mix.protocal.xml.model.XmlModel;
import com.xuan.mix.protocal.xml.model.XmlNodeModel;
import com.xuan.mix.protocal.xml.model.XmlSchemaModel;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author xuan
 * @since 2020/11/10
 */
public class XmlReader {

    public static XmlModel read(String xml, String charset) {
        if (null == xml) {
            return null;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            InputStream is = new ByteArrayInputStream(xml.getBytes(charset));
            Document document = builder.parse(is);

            //parse protocol
            XmlModel xmlModel = new XmlModel();
            XmlSchemaModel schemaModel = new XmlSchemaModel();
            schemaModel.setEncoding(document.getInputEncoding());
            schemaModel.setVersion(document.getXmlVersion());
            xmlModel.setSchemaModel(schemaModel);

            //parse node
            NodeList nodeList = document.getChildNodes();
            if (null == nodeList || nodeList.getLength() == 0) {
                return xmlModel;
            }

            Node rootNode = nodeList.item(0);
            XmlNodeModel nodeModel = doNode2Model(rootNode);
            xmlModel.setNodeModel(nodeModel);
            return xmlModel;
        } catch (Exception e) {
            throw new RuntimeException("XmlReader@read_Exception.", e);
        }
    }

    private static XmlNodeModel doNode2Model(Node node) {
        if (null == node) {
            return null;
        }

        XmlNodeModel model = new XmlNodeModel(node.getNodeName());

        //attr
        NamedNodeMap namedNodeMap = node.getAttributes();
        if (null != namedNodeMap && namedNodeMap.getLength() > 0) {
            for (int i = 0, n = namedNodeMap.getLength(); i < n; i++) {
                org.w3c.dom.Node attr = namedNodeMap.item(i);
                model.addAttr(attr.getNodeName(), attr.getNodeValue());
            }
        }

        //child
        NodeList nodeList = node.getChildNodes();
        if (null != nodeList && nodeList.getLength() > 0) {
            for (int i = 0, n = nodeList.getLength(); i < n; i++) {
                org.w3c.dom.Node child = nodeList.item(i);

                if ("#cdata-section".equals(child.getNodeName())) {
                    model.addCdata(child.getNodeValue());
                } else if ("#text".equals(child.getNodeName())) {
                    String nodeValue = child.getNodeValue();
                    if (null != nodeValue && !"".equals(nodeValue.trim())) {
                        model.addText(child.getNodeValue());
                    }
                } else {
                    model.addChild(doNode2Model(child));
                }
            }
        }
        return model;
    }

}
