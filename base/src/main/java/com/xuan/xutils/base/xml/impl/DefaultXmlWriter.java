package com.xuan.xutils.base.xml.impl;

import java.util.HashMap;
import java.util.Map.Entry;

import com.xuan.xutils.base.xml.XmlWriter;
import com.xuan.xutils.base.xml.model.Xml;
import com.xuan.xutils.base.xml.model.XmlNode;
import com.xuan.xutils.base.xml.model.XmlSchema;

/**
 * 默认JDK自带API实现
 *
 * @author xuan
 * @since 2020/11/10
 */
public class DefaultXmlWriter implements XmlWriter {
    /**
     * cdata格式化
     */
    private final static String FORMAT_CDATA = "<![CDATA[%s]]>";
    /**
     * 分割符
     */
    private final static String SP = System.getProperty("line.separator");
    /**
     * 空格
     */
    private final static String BLANK_SPACE = " ";
    /**
     * EQ
     */
    private final static String EQ = "=";
    /**
     * QUOT
     */
    private final static String QUOT = "\"";
    /**
     * 节点开始结束
     */
    private final static String LEFT_ARROW = "<";
    private final static String LEFT_ARROW_CLOSE = "</";
    private final static String RIGHT_ARROW = ">";
    private final static String RIGHT_ARROW_CLOSE = "/>";
    /**
     * schema开始结束
     */
    private final static String SCHEMA_START = "<?xml";
    private final static String SCHEMA_END = "?>";
    /**
     * 锁进空格
     */
    private final static int TAB_BLANK_SPACE_NUM = 2;

    /**
     * 属性转义替换
     */
    private final static HashMap<String, String> ATTR_FILTER_MAP = new HashMap<>();

    static {
        ATTR_FILTER_MAP.put("<", "&lt;");
        ATTR_FILTER_MAP.put(">", "&gt;");
    }

    @Override
    public String write(Xml xml) {
        try {
            if (null == xml) {
                return "";
            }

            StringBuilder xmlBuilder = new StringBuilder();
            doAppendSchema(xmlBuilder, xml.getSchema());
            xmlBuilder.append(SP);
            doAppendNodeRecursion(xmlBuilder, xml.getRootNode(), 0);
            return xmlBuilder.toString();

        } catch (Exception e) {
            throw new RuntimeException("DefaultXmlWriter@write_Exception.", e);
        }
    }

    /**
     * 构建协议
     *
     * @param xmlBuilder 构建器
     * @param xmlSchema  XML协议
     */
    private static void doAppendSchema(StringBuilder xmlBuilder, XmlSchema xmlSchema) {
        if (null == xmlSchema) {
            return;
        }

        //例如：<?xml version="1.0" encoding="UTF-8"?>
        xmlBuilder.append(SCHEMA_START).append(BLANK_SPACE);
        if (xmlSchema.getVersion().trim().length() > 0) {
            xmlBuilder.append("version=").append(QUOT).append(xmlSchema.getVersion()).append(QUOT);
        }
        if (xmlSchema.getEncoding().trim().length() > 0) {
            xmlBuilder.append(BLANK_SPACE).append("encoding=").append(QUOT).append(xmlSchema.getEncoding()).append(
                QUOT);
        }
        xmlBuilder.append(SCHEMA_END);
    }

    /**
     * 递归构建节点文本
     *
     * @param xmlBuilder    构建器
     * @param xmlNode       当前节点
     * @param blankSpaceNum 缩紧空格
     */
    private static void doAppendNodeRecursion(StringBuilder xmlBuilder, XmlNode xmlNode, int blankSpaceNum) {
        if (null == xmlNode) {
            return;
        }

        //拼接节点名称，例如：<input
        xmlBuilder.append(getBlankSpaceStr(blankSpaceNum)).append(LEFT_ARROW).append(xmlNode.getName());

        //拼接节点属性，例如：<input name="abc" tag="123"
        if (null != xmlNode.getAttributeMap()) {
            for (Entry<String, String> attrEntry : xmlNode.getAttributeMap().entrySet()) {
                if (null != attrEntry.getValue()) {
                    xmlBuilder.append(BLANK_SPACE)
                        .append(attrEntry.getKey())
                        .append(EQ)
                        .append(QUOT)
                        .append(filterSpecialCharacters(attrEntry.getValue()))
                        .append(QUOT);
                }
            }
        }

        //拼接关闭符号
        if (null == xmlNode.getCdataList() && null == xmlNode.getTextList() && null == xmlNode.getChildList()) {
            //无子节点的情况需要斜杠关闭，like：<input name="abc" tag="123" />
            xmlBuilder.append(RIGHT_ARROW_CLOSE);
            return;
        } else {
            //含子节点的情况，直接用右尖括号关闭，like：<input name="abc" tag="123">
            xmlBuilder.append(RIGHT_ARROW);
        }

        //含cdata的情况，例如：<input name="abc" tag="123"><![CDATA[value1]]><![CDATA[value2]]>
        if (null != xmlNode.getCdataList()) {
            for (String cdata : xmlNode.getCdataList()) {
                xmlBuilder.append(String.format(FORMAT_CDATA, cdata));
            }
        }

        //含text的情况，例如：<input name="abc" tag="123"><![CDATA[]]><![CDATA[value2]]>123
        if (null != xmlNode.getTextList()) {
            for (String text : xmlNode.getTextList()) {
                xmlBuilder.append(text);
            }
        }

        //拼接子节点，例如：
        // <input name="abc" tag="123"><![CDATA[value1]]><![CDATA[value2]]>123
        //       <a>...</a>
        //       <b>...</b>
        if (null != xmlNode.getChildList()) {
            for (XmlNode childNode : xmlNode.getChildList()) {
                xmlBuilder.append(SP);
                doAppendNodeRecursion(xmlBuilder, childNode, blankSpaceNum + TAB_BLANK_SPACE_NUM);
            }
            xmlBuilder.append(SP).append(getBlankSpaceStr(blankSpaceNum));
        }

        //最后节点关闭，例如：
        // <input name="abc" tag="123"><![CDATA[value1]]><![CDATA[value2]]>123
        //       <a>...</a>
        //       <b>...</b>
        //    </input>
        xmlBuilder.append(LEFT_ARROW_CLOSE)
            .append(xmlNode.getName())
            .append(RIGHT_ARROW);
    }

    /**
     * 获取多个空格
     *
     * @param blankSpaceNum 指定空格数
     * @return 多个空格串
     */
    private static String getBlankSpaceStr(int blankSpaceNum) {
        StringBuilder blankSpaceStr = new StringBuilder();
        for (int i = 0; i < blankSpaceNum; i++) {
            blankSpaceStr.append(BLANK_SPACE);
        }
        return blankSpaceStr.toString();
    }

    /**
     * 特殊字符替换
     *
     * @param xmlContent 替换前文本
     * @return 替换后文本
     */
    private static String filterSpecialCharacters(String xmlContent) {
        for (Entry<String, String> entry : ATTR_FILTER_MAP.entrySet()) {
            xmlContent = xmlContent.replaceAll(entry.getKey(), entry.getValue());
        }
        return xmlContent;
    }

}
