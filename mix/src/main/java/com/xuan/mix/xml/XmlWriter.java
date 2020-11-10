package com.xuan.mix.xml;

import java.util.HashMap;
import java.util.Map.Entry;

import com.xuan.mix.xml.model.XmlModel;
import com.xuan.mix.xml.model.XmlNodeModel;
import com.xuan.mix.xml.model.XmlSchemaModel;

/**
 * @author xuan
 * @since 2020/11/10
 */
public class XmlWriter {
    /**
     * 数据包裹格式
     */
    private final static String FORMAT_CDATA = "<![CDATA[%s]]>";
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
    private final static String EQ = "=";
    /**
     * 双引号
     */
    private final static String QUOT = "\"";
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
     * schema开始结束
     */
    private final static String SCHEMA_START = "<?xml";
    private final static String SCHEMA_END = "?>";
    /**
     * tab缩进空格数
     */
    private final static int TAB_BLANK_SPACE_NUM = 2;

    /**
     * 属性需要转义的关键字
     */
    private final static HashMap<String, String> ATTR_FILTER_MAP = new HashMap<>();

    static {
        ATTR_FILTER_MAP.put("<", "&lt;");
        ATTR_FILTER_MAP.put(">", "&gt;");
    }

    public static String write(XmlModel xmlModel) {
        try {
            if (null == xmlModel) {
                return "";
            }

            StringBuilder xml = new StringBuilder();

            doAppendNode(xml, xmlModel.getNodeModel(), 0);
            return xml.toString();

        } catch (Exception e) {
            throw new RuntimeException("XmlWriter@write_Exception.", e);
        }
    }

    private static void doAppendSchema(StringBuilder xml, XmlSchemaModel schemaModel) {
        if (null == schemaModel) {
            return;
        }
        //<?xml version="1.0" encoding="UTF-8"?>
        xml.append(SCHEMA_START).append(BLANK_SPACE);
        if (schemaModel.getVersion().trim().length() > 0) {
            xml.append("version=").append(QUOT).append(schemaModel.getVersion()).append(QUOT);
        }
        if (schemaModel.getEncoding().trim().length() > 0) {
            xml.append(BLANK_SPACE).append("encoding=").append(QUOT).append(schemaModel.getEncoding()).append(QUOT);
        }
        xml.append(SCHEMA_END);
    }

    private static void doAppendNode(StringBuilder xml, XmlNodeModel model, int blankSpaceNum) {
        if (null == model) {
            return;
        }

        //拼接节点name，如：<input
        xml.append(getBlankSpaceStr(blankSpaceNum)).append(LEFT_ARROW).append(model.getName());

        //拼接节点属性，如：<input name="abc" tag="123"
        if (null != model.getAttr()) {
            for (Entry<String, String> attrEntry : model.getAttr().entrySet()) {
                if (null != attrEntry.getValue()) {
                    xml.append(BLANK_SPACE)
                        .append(attrEntry.getKey())
                        .append(EQ)
                        .append(QUOT)
                        .append(filterSpecialCharacters(attrEntry.getValue()))
                        .append(QUOT);
                }
            }
        }

        if (null == model.getCdataList() && null == model.getTextList() && null == model.getChildList()) {
            //没有孩子节点，且也没有值数据，直接关闭返回，如：<input name="abc" tag="123" />
            xml.append(RIGHT_ARROW_CLOSE);
            return;
        } else {
            //需要往下拼接孩子节点或者值节点，如：<input name="abc" tag="123">
            xml.append(RIGHT_ARROW);
        }

        //拼接cdata，如：<input name="abc" tag="123"><![CDATA[value1]]><![CDATA[value2]]>
        if (null != model.getCdataList()) {
            for (String cdata : model.getCdataList()) {
                xml.append(String.format(FORMAT_CDATA, cdata));
            }
        }

        //拼接text，如：<input name="abc" tag="123"><![CDATA[value1]]><![CDATA[value2]]>123
        if (null != model.getTextList()) {
            for (String text : model.getTextList()) {
                xml.append(text);
            }
        }

        //拼接孩子节点，
        // 如：<input name="abc" tag="123"><![CDATA[value1]]><![CDATA[value2]]>123
        //       <a>...</a>
        //       <b>...</b>
        if (null != model.getChildList()) {
            for (XmlNodeModel child : model.getChildList()) {
                xml.append(SP);
                doAppendNode(xml, child, blankSpaceNum + TAB_BLANK_SPACE_NUM);
            }
            xml.append(SP).append(getBlankSpaceStr(blankSpaceNum));
        }

        //换行收尾，
        // 如：<input name="abc" tag="123"><![CDATA[value1]]><![CDATA[value2]]>123
        //       <a>...</a>
        //       <b>...</b>
        //    </input>
        xml.append(LEFT_ARROW_CLOSE)
            .append(model.getName())
            .append(RIGHT_ARROW);
    }

    private static String getBlankSpaceStr(int blankSpaceNum) {
        StringBuilder blankSpaceStr = new StringBuilder();
        for (int i = 0; i < blankSpaceNum; i++) {
            blankSpaceStr.append(BLANK_SPACE);
        }
        return blankSpaceStr.toString();
    }

    private static String filterSpecialCharacters(String xml) {
        for (Entry<String, String> entry : ATTR_FILTER_MAP.entrySet()) {
            xml = xml.replaceAll(entry.getKey(), entry.getValue());
        }
        return xml;
    }

}
