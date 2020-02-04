package com.xuan.mix.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 * @author xuan
 * @since 2019/10/22
 */
public class XmlUtil {
    /**
     * 一般xml的协议头
     */
    public final static String PROTOCAL = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    /**
     * 分割符号
     */
    private final static String SP = System.getProperty("line.separator");
    /**
     * 空格
     */
    private final static String BLANK_SPACE = " ";
    /**
     * 等于符号
     */
    private final static String EQUAL = "=";
    /**
     * 双引号
     */
    private final static String DB_QUOT = "\"";
    /**
     * 左尖括号
     */
    private final static String LEFT_ARROW = "<";
    private final static String LEFT_ARROW_CLOSE = "</";
    /**
     * 右尖括号
     */
    private final static String RIGHT_ARROW = ">";
    private final static String RIGHT_ARROW_CLOSE = "/>";
    /**
     * tab缩进空格数
     */
    private final static int TAB_BLANK_SPACE_NUM = 2;
    /**
     * 数据包裹格式
     */
    private final static String CDATA_FORMAT = "<![CDATA[#]]>";

    /**
     * 属性需要转义的关键字
     */
    private final static HashMap<String, String> ATTR_FILTER_MAP = new HashMap<>();

    static {
        ATTR_FILTER_MAP.put("<", "&lt;");
        ATTR_FILTER_MAP.put(">", "&gt;");
    }

    /**
     * node to xml
     *
     * @param node node
     * @return xml
     */
    public static String node2Xml(Node node) {
        if (null == node) {
            return "";
        }

        StringBuilder xml = new StringBuilder();
        doNode2Xml(xml, node, 0);
        return xml.toString();
    }

    private static void doNode2Xml(StringBuilder xml, Node node, int blankSpaceNum) {
        //attr
        xml.append(getBlankSpaceStr(blankSpaceNum) + LEFT_ARROW + node.getType());
        if (null != node.getAttr()) {
            for (Entry<String, String> attrEntry : node.getAttr().entrySet()) {
                if (null != attrEntry.getValue()) {
                    xml.append(
                        BLANK_SPACE + attrEntry.getKey() + EQUAL + DB_QUOT + filterSpecialCharacters(
                            attrEntry.getValue())
                            + DB_QUOT);
                }
            }
        }

        if (null != node.getChildList()) {
            xml.append(RIGHT_ARROW);
            for (Node child : node.getChildList()) {
                xml.append(SP);
                doNode2Xml(xml, child, blankSpaceNum + TAB_BLANK_SPACE_NUM);
            }
            xml.append(SP);
            xml.append(getBlankSpaceStr(blankSpaceNum) + LEFT_ARROW_CLOSE + node.getType() + RIGHT_ARROW);
        } else if (null != node.getValue()) {
            xml.append(RIGHT_ARROW);
            xml.append(getNodeValue(node));
            xml.append(LEFT_ARROW_CLOSE + node.getType() + RIGHT_ARROW);
        } else {
            xml.append(RIGHT_ARROW_CLOSE);
        }
    }

    private static String getBlankSpaceStr(int blankSpaceNum) {
        StringBuilder blankSpaceStr = new StringBuilder();
        for (int i = 0; i < blankSpaceNum; i++) {
            blankSpaceStr.append(BLANK_SPACE);
        }
        return blankSpaceStr.toString();
    }

    private static String getNodeValue(Node node) {
        if (node.isValueData()) {
            return CDATA_FORMAT.replace("#", node.getValue());
        }
        return node.getValue();
    }

    private static String filterSpecialCharacters(String xml) {
        for (Entry<String, String> entry : ATTR_FILTER_MAP.entrySet()) {
            xml = xml.replaceAll(entry.getKey(), entry.getValue());
        }
        return xml;
    }

    /**
     * xml to node
     *
     * @param xml xml
     * @return node
     */
    public static Node xml2Node(String xml, String charset) {
        if (null == xml) {
            return null;
        }

        try {
            InputStream xmlInputStream = new ByteArrayInputStream(xml.getBytes(charset));
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlInputStream);

            NodeList nodeList = document.getChildNodes();
            if (null == nodeList || nodeList.getLength() == 1) {
                return null;
            }

            org.w3c.dom.Node rootNode = nodeList.item(0);
            return doNode2Node(rootNode);

        } catch (Exception e) {
            throw new RuntimeException("XmlUtil@xml2Node_exception.", e);
        }
    }

    private static Node doNode2Node(org.w3c.dom.Node w3cNode) {
        if (null == w3cNode) {
            return null;
        }

        Node node = new Node(w3cNode.getNodeName());

        //attr
        NamedNodeMap namedNodeMap = w3cNode.getAttributes();
        if (null != namedNodeMap && namedNodeMap.getLength() > 0) {
            for (int i = 0, n = namedNodeMap.getLength(); i < n; i++) {
                org.w3c.dom.Node attr = namedNodeMap.item(i);
                node.addAttr(attr.getNodeName(), attr.getNodeValue());
            }
        }

        //child
        NodeList nodeList = w3cNode.getChildNodes();
        if (null != nodeList && nodeList.getLength() > 0) {
            for (int i = 0, n = nodeList.getLength(); i < n; i++) {
                org.w3c.dom.Node child = nodeList.item(i);

                if (!(child instanceof Element)) {
                    if ("#cdata-section".equals(child.getNodeName())) {
                        node.setValue(child.getNodeValue());
                    }
                } else {
                    node.addChild(doNode2Node(child));
                }
            }
        }
        return node;
    }

    public static void main(String[] args) {
        testNode2Xml();
        //testXml2Node();
    }

    private static void testNode2Xml() {
        Node bpm = new Node("bpm");
        bpm.addAttr("code", "bpm.ktvExample");
        bpm.addAttr("name", "xxxx");

        Node autoTask = Node.buildNode("autoTask", bpm);
        autoTask.addAttr("a", "111");
        autoTask.addAttr("b", "222");

        Node decition = Node.buildNode("decition", bpm);
        decition.addAttr("aa", "111aaa");
        decition.addAttr("ba", "222aaa");
        decition.setValue("cDatassssss");

        Node decition1 = Node.buildNode("decition1", bpm);
        decition1.addAttr("ddd", "vvv");
        decition1.setValue("noCData");
        decition1.setValueData(false);

        System.out.println(node2Xml(bpm));
    }

    //private static void testXml2Node() {
    //    String str = "<bpm code=\"bpm.ktvExample\" name=\"中文哦\">\n"
    //        + "  <autoTask a=\"111\" b=\"222\"/>\n"
    //        + "  <decition aa=\"111aaa\" ba=\"222aaa\"><![CDATA[中文]]></decition>\n"
    //        + "  <decition1 ddd=\"vvv\">noCData</decition1>\n"
    //        + "</bpm>";
    //
    //    Node node = xml2Node(str, "utf-8");
    //    System.out.println(node.getType());
    //    System.out.println(node.getAttr("name"));
    //    System.out.println(node.getAttr().size());
    //    System.out.println(node.getChildList().size());
    //    System.out.println(node.getFistChild("decition").getValue());
    //    System.out.println(node.getFistChild("decition1").getValue());
    //}

}
