package com.xuan.spi.framework.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * XML转成模型数据
 *
 * @author xuan
 * @since 2020/11/10
 */
public class AppConfigReader {

    public static AppConfig read(URL url) {
        if (null == url) {
            return null;
        }

        String content = getContextFromUrl(url);
        if (null == content) {
            return null;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            InputStream is = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
            Document document = builder.parse(is);

            //parse node
            NodeList nodeList = document.getChildNodes();
            if (null == nodeList || nodeList.getLength() == 0) {
                return null;
            }

            AppConfig appConfig = new AppConfig();

            Node spi2AppNode = nodeList.item(0);

            fetchAppConfigAttrs(appConfig, spi2AppNode);
            fetchAppConfigChild(appConfig, spi2AppNode);

            return appConfig;
        } catch (Exception e) {
            throw new RuntimeException("AppConfigReader@read_Exception.", e);
        }
    }

    private static void fetchAppConfigAttrs(AppConfig config, Node spi2AppNode) {
        NamedNodeMap attributes = spi2AppNode.getAttributes();
        if (null == attributes) {
            return;
        }
        Node nameNode = attributes.getNamedItem("name");
        if (null != nameNode) {
            config.setName(nameNode.getNodeValue());
        }
        Node descriptionNode = attributes.getNamedItem("description");
        if (null != descriptionNode) {
            config.setDescription(descriptionNode.getNodeValue());
        }
        Node versionNode = attributes.getNamedItem("version");
        if (null != versionNode) {
            config.setVersion(versionNode.getNodeValue());
        }
        Node appClassNode = attributes.getNamedItem("appClass");
        if (null != appClassNode) {
            config.setAppClass(appClassNode.getNodeValue());
        }
    }

    private static void fetchAppConfigChild(AppConfig config, Node spi2AppNode) {
        NodeList childList = spi2AppNode.getChildNodes();
        for (int i = 0, n = childList.getLength(); i < n; i++) {
            Node child = childList.item(i);
            if ("supported-biz-codes".equals(child.getNodeName())) {
                config.setSupportedBizCodes(getSupportedBizCodes(child));
            } else if ("extension-points-groups".equals(child.getNodeName())) {
                config.setExtensionPointsGroups(getExtensionPointsGroups(child));
            }
        }
    }

    private static List<String> getSupportedBizCodes(Node supportedBizCodesNode) {
        List<String> supportedBizCodeList = new ArrayList<>();

        NodeList childList = supportedBizCodesNode.getChildNodes();
        for (int i = 0, n = childList.getLength(); i < n; i++) {
            Node child = childList.item(i);
            if ("supported-biz-code".equals(child.getNodeName())) {
                NamedNodeMap attrs = child.getAttributes();
                if (null != attrs) {
                    Node valueNode = attrs.getNamedItem("value");
                    if (null != valueNode) {
                        supportedBizCodeList.add(valueNode.getNodeValue());
                    }
                }
            }
        }
        return supportedBizCodeList;
    }

    private static List<ExtensionPointsGroupConfig> getExtensionPointsGroups(Node extensionPointsGroupsNode) {
        List<ExtensionPointsGroupConfig> extensionPointsGroupConfigList = new ArrayList<>();

        NodeList childList = extensionPointsGroupsNode.getChildNodes();
        for (int i = 0, n = childList.getLength(); i < n; i++) {
            Node child = childList.item(i);
            if ("extension-points-group".equals(child.getNodeName())) {
                NamedNodeMap attrs = child.getAttributes();
                if (null != attrs) {
                    ExtensionPointsGroupConfig extensionPointsGroupConfig = new ExtensionPointsGroupConfig();

                    Node classNode = attrs.getNamedItem("class");
                    if (null != classNode) {
                        extensionPointsGroupConfig.setClazz(classNode.getNodeValue());
                    }
                    Node priorityNode = attrs.getNamedItem("priority");
                    if (null != priorityNode) {
                        extensionPointsGroupConfig.setPriority(Integer.parseInt(priorityNode.getNodeValue()));
                    }

                    extensionPointsGroupConfigList.add(extensionPointsGroupConfig);
                }
            }
        }
        return extensionPointsGroupConfigList;
    }

    private static String getContextFromUrl(URL url) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            StringBuilder context = new StringBuilder();
            while ((line = br.readLine()) != null) {
                context.append(line);
            }
            return context.toString();
        } catch (Exception e) {
            return null;
        }
    }

}
