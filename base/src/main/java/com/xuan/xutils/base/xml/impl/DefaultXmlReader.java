package com.xuan.xutils.base.xml.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.xuan.xutils.base.xml.XmlReader;
import com.xuan.xutils.base.xml.model.Xml;
import com.xuan.xutils.base.xml.model.XmlNode;
import com.xuan.xutils.base.xml.model.XmlSchema;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 默认JDK自带API实现
 *
 * @author xuan
 * @since 2020/11/10
 */
public class DefaultXmlReader implements XmlReader {

    private final static String NODENAME_CDATA_SECTION = "#cdata-section";

    private final static String NODENAME_TEXT = "#text";

    @Override
    public Xml read(String xmlContent, String charset) {
        if (null == xmlContent) {
            return null;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            InputStream is = new ByteArrayInputStream(xmlContent.getBytes(charset));
            Document document = builder.parse(is);

            //XML
            Xml xml = new Xml();

            //读取XML协议
            XmlSchema xmlSchema = new XmlSchema();
            xmlSchema.setEncoding(document.getInputEncoding());
            xmlSchema.setVersion(document.getXmlVersion());
            xml.setSchema(xmlSchema);

            //递归读取XML节点
            NodeList nodeList = document.getChildNodes();
            if (null == nodeList || nodeList.getLength() == 0) {
                return xml;
            }
            Node rootNode = nodeList.item(0);
            XmlNode rootXmlNode = fetchXmlNodeRecursion(rootNode);
            xml.setRootNode(rootXmlNode);
            return xml;
        } catch (Exception e) {
            throw new RuntimeException("读取XML文件异常，原因：" + e.getMessage(), e);
        }
    }

    /**
     * 递归构建XmlNode节点
     *
     * @param node node
     * @return xmlNode
     */
    private XmlNode fetchXmlNodeRecursion(Node node) {
        if (null == node) {
            return null;
        }

        XmlNode xmlNode = XmlNode.of(node.getNodeName());

        //获取属性
        NamedNodeMap namedNodeMap = node.getAttributes();
        if (null != namedNodeMap && namedNodeMap.getLength() > 0) {
            for (int i = 0, n = namedNodeMap.getLength(); i < n; i++) {
                Node attr = namedNodeMap.item(i);
                xmlNode.addAttribute(attr.getNodeName(), attr.getNodeValue());
            }
        }

        //获取孩子节点
        NodeList nodeList = node.getChildNodes();
        if (null != nodeList && nodeList.getLength() > 0) {
            for (int i = 0, n = nodeList.getLength(); i < n; i++) {
                Node child = nodeList.item(i);

                if (NODENAME_CDATA_SECTION.equals(child.getNodeName())) {
                    xmlNode.addCdata(child.getNodeValue());
                } else if (NODENAME_TEXT.equals(child.getNodeName())) {
                    String nodeValue = child.getNodeValue();
                    if (null != nodeValue && !"".equals(nodeValue.trim())) {
                        xmlNode.addText(child.getNodeValue());
                    }
                } else {
                    xmlNode.addChild(fetchXmlNodeRecursion(child));
                }
            }
        }
        return xmlNode;
    }

}
