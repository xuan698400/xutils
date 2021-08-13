package com.extp.framework.core.plugin;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.extp.framework.core.plugin.model.BizCode;
import com.extp.framework.core.plugin.model.ExtImpl;
import com.extp.framework.core.plugin.model.Plugin;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author xuan
 * @since 2021/7/28
 */
public class XmlParser {

    public static Plugin parser(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            InputStream is = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
            Document document = builder.parse(is);

            NodeList nodeList = document.getChildNodes();
            if (null == nodeList || nodeList.getLength() < 1) {
                return null;
            }
            Node node = nodeList.item(0);
            return parserPlugin(node);
        } catch (Exception e) {
            //Ignore
        }
        return null;
    }

    private static Plugin parserPlugin(Node node) {
        Plugin plugin = new Plugin();
        plugin.setCode(getAttribute(node, "code"));
        plugin.setName(getAttribute(node, "name"));

        NodeList nodeList = node.getChildNodes();
        if (null != nodeList && nodeList.getLength() > 0) {
            for (int i = 0, n = nodeList.getLength(); i < n; i++) {
                Node childNode = nodeList.item(i);
                if ("biz-codes".equals(childNode.getNodeName())) {
                    plugin.setBizCodeList(parserBizCodes(childNode));
                } else if ("ext-impls".equals(childNode.getNodeName())) {
                    plugin.setExtImplList(parserExtImpls(childNode));
                }
            }
        }
        return plugin;
    }

    private static List<ExtImpl> parserExtImpls(Node node) {
        List<ExtImpl> extImplList = new ArrayList<>();

        NodeList nodeList = node.getChildNodes();
        if (null != nodeList && nodeList.getLength() > 0) {
            for (int i = 0, n = nodeList.getLength(); i < n; i++) {
                Node childNode = nodeList.item(i);
                if ("ext-impl".equals(childNode.getNodeName())) {
                    ExtImpl extImpl = parserExtImpl(childNode);
                    if (null != extImpl) {
                        extImplList.add(extImpl);
                    }
                }
            }
        }
        return extImplList;
    }

    private static ExtImpl parserExtImpl(Node node) {
        ExtImpl extImpl = new ExtImpl();
        extImpl.setExt(getAttribute(node, "ext"));
        extImpl.setImpl(getAttribute(node, "impl"));
        return extImpl;
    }

    private static List<BizCode> parserBizCodes(Node node) {
        List<BizCode> bizCodeList = new ArrayList<>();

        NodeList nodeList = node.getChildNodes();
        if (null != nodeList && nodeList.getLength() > 0) {
            for (int i = 0, n = nodeList.getLength(); i < n; i++) {
                Node childNode = nodeList.item(i);
                if ("biz-code".equals(childNode.getNodeName())) {
                    BizCode bizCode = parserBizCode(childNode);
                    if (null != bizCode) {
                        bizCodeList.add(bizCode);
                    }
                }
            }
        }
        return bizCodeList;
    }

    private static BizCode parserBizCode(Node node) {
        BizCode bizCode = new BizCode();
        bizCode.setValue(getAttribute(node, "value"));
        return bizCode;
    }

    private static String getAttribute(Node node, String key) {
        return node.getAttributes().getNamedItem(key).getNodeValue();
    }

}
