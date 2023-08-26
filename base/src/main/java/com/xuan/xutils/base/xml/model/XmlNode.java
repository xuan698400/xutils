package com.xuan.xutils.base.xml.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * XML节点
 *
 * @author xuan
 * @since 2019/10/22
 */
public class XmlNode {

    /**
     * 节点类型
     * 例如：<input>...</input>中的input值
     */
    private String name;
    /**
     * 节点包裹的值
     * 例如：<input><![CDATA[value1]]><![CDATA[value2]]>value3</input>中的value1和value2
     */
    private List<String> cdataList;
    /**
     * 节点未包裹的值
     * 例如：<input><![CDATA[value1]]><![CDATA[value2]]>value3</input>中的value3
     */
    private List<String> textList;
    /**
     * 节点属性
     * 例如：<input name="abc" tag="123"><![CDATA[value]]></input>中的name="abc"、tag="123"
     */
    private Map<String, String> attributeMap;
    /**
     * 孩子节点列表
     * 例如：
     * <input name="abc">
     * <a>...</a>
     * <b>...</b>
     * </input>
     * 中的a节点和b节点
     */
    private List<XmlNode> childList;

    public static XmlNode of(String name) {
        XmlNode node = new XmlNode();
        node.setName(name);
        return node;
    }

    /**
     * 构建一个节点，并挂载到指定父节点下
     *
     * @param name       节点类型
     * @param parentNode 父节点
     * @return 新建节点
     */
    public static XmlNode of(String name, XmlNode parentNode) {
        XmlNode childNode = new XmlNode();
        childNode.setName(name);
        parentNode.addChild(childNode);
        return childNode;
    }

    /**
     * 节点新增属性
     *
     * @param name  属性值
     * @param value 属性值，为null时不添加
     */
    public void addAttribute(String name, String value) {
        if (null == attributeMap) {
            attributeMap = new HashMap<>();
        }
        if (null != value) {
            attributeMap.put(name, value);
        }
    }

    /**
     * 获取节点属性值
     *
     * @param name 属性名
     * @return 属性值
     */
    public String getAttribute(String name) {
        if (null == attributeMap) {
            return null;
        }
        return attributeMap.get(name);
    }

    /**
     * 添加子节点
     *
     * @param childNode 子节点
     */
    public void addChild(XmlNode childNode) {
        if (null == childList) {
            childList = new ArrayList<>();
        }
        childList.add(childNode);
    }

    /**
     * 获取子节点中的第一个节点
     *
     * @return 第一个节点
     */
    public XmlNode getFistChild() {
        if (null == childList || childList.isEmpty()) {
            return null;
        }

        return childList.get(0);
    }

    /**
     * 获取指定节点类型子节点中的第一个节点
     *
     * @param name 指定节点类型
     * @return 符合节点类型的第一个子节点
     */
    public XmlNode getFistChild(String name) {
        List<XmlNode> nameChildList = getChildList(name);
        if (null == nameChildList || nameChildList.isEmpty()) {
            return null;
        }

        return nameChildList.get(0);
    }

    /**
     * 获取指定节点类型的所有子节点
     *
     * @param name 指定节点类型
     * @return 符合节点类型的子节点列表
     */
    public List<XmlNode> getChildList(String name) {
        if (null == childList) {
            return null;
        }
        List<XmlNode> nameChildList = new ArrayList<>();
        for (XmlNode node : childList) {
            if (Objects.equals(node.getName(), name)) {
                nameChildList.add(node);
            }
        }
        return nameChildList;
    }

    /**
     * 删除清理所有子节点
     */
    public void clearChild() {
        if (null == childList || childList.isEmpty()) {
            return;
        }
        childList.clear();
    }

    /**
     * 添加一个CDATA值
     *
     * @param cdata CDATA值
     */
    public void addCdata(String cdata) {
        if (null == cdataList) {
            cdataList = new ArrayList<>();
        }
        cdataList.add(cdata);
    }

    /**
     * 添加节点TEXT值
     *
     * @param text TEXT值
     */
    public void addText(String text) {
        if (null == textList) {
            textList = new ArrayList<>();
        }
        textList.add(text);
    }

    /**
     * 获取节点name
     *
     * @return 节点name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置节点name
     *
     * @param name 节点name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取节点所有属性
     *
     * @return 节点所有属性
     */
    public Map<String, String> getAttributeMap() {
        return attributeMap;
    }

    /**
     * 覆盖设置节点所有属性
     *
     * @param attributeMap 节点属性MAP
     */
    public void setAttributeMap(Map<String, String> attributeMap) {
        this.attributeMap = attributeMap;
    }

    /**
     * 获取节点所有孩子节点
     *
     * @return 孩子节点列表
     */
    public List<XmlNode> getChildList() {
        return childList;
    }

    /**
     * 覆盖设置节点所有孩子节点
     *
     * @param childList 孩子节点列表
     */
    public void setChildList(List<XmlNode> childList) {
        this.childList = childList;
    }

    /**
     * 获取所有CDATA值列表
     *
     * @return CDATA值列表
     */
    public List<String> getCdataList() {
        return cdataList;
    }

    /**
     * 覆盖设置所有CDATA值列表
     *
     * @param cdataList CDATA值列表
     */
    public void setCdataList(List<String> cdataList) {
        this.cdataList = cdataList;
    }

    /**
     * 获取所有节点TEXT值
     *
     * @return 节点TEXT值列表
     */
    public List<String> getTextList() {
        return textList;
    }

    /**
     * 覆盖设置所有TEXT值列表
     *
     * @param textList TEXT值列表
     */
    public void setTextList(List<String> textList) {
        this.textList = textList;
    }

}
