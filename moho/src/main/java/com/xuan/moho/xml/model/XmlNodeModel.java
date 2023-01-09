package com.xuan.moho.xml.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * XML协议，节点模型
 *
 * @author xuan
 * @since 2019/10/22
 */
public class XmlNodeModel {

    /**
     * 节点类型.
     * 例如<input>...</input>中的input值
     */
    private String name;
    /**
     * 节点包裹的值.
     * 例如：<input><![CDATA[value1]]><![CDATA[value2]]>value3</input>中的value1和value2
     */
    private List<String> cdataList;
    /**
     * 节点未包裹的值.
     * 例如：<input><![CDATA[value1]]><![CDATA[value2]]>value3</input>中的value3
     */
    private List<String> textList;
    /**
     * 节点属性.
     * 例如：<input name="abc" tag="123"><![CDATA[value]]></input>中的name="abc"、tag="123"
     */
    private Map<String, String> attr;
    /**
     * 孩子节点列表.
     * 例如：
     * <input name="abc">
     * <a>...</a>
     * <b>...</b>
     * </input>
     * 中的a节点和b节点
     */
    private List<XmlNodeModel> childList;

    public XmlNodeModel(String name) {
        this.name = name;
    }

    /**
     * 构建一个节点，并挂载到指定父节点下
     *
     * @param name   节点类型
     * @param parent 父节点
     * @return 新建节点
     */
    public static XmlNodeModel buildNode(String name, XmlNodeModel parent) {
        XmlNodeModel node = new XmlNodeModel(name);
        parent.addChild(node);
        return node;
    }

    /**
     * 节点新增属性
     *
     * @param key   属性值
     * @param value 属性value，为null时不添加
     */
    public void addAttr(String key, String value) {
        if (null == attr) {
            attr = new HashMap<>();
        }
        if (null != value) {
            attr.put(key, value);
        }
    }

    /**
     * 获取节点属性值
     *
     * @param key 属性key
     * @return 属性值
     */
    public String getAttr(String key) {
        if (null == attr) {
            return null;
        }
        return attr.get(key);
    }

    /**
     * 添加子节点
     *
     * @param node 子节点
     */
    public void addChild(XmlNodeModel node) {
        if (null == childList) {
            childList = new ArrayList<>();
        }
        childList.add(node);
    }

    /**
     * 获取子节点中的第一个节点
     *
     * @return 第一个节点
     */
    public XmlNodeModel getFistChild() {
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
    public XmlNodeModel getFistChild(String name) {
        List<XmlNodeModel> nameChildList = getChildList(name);
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
    public List<XmlNodeModel> getChildList(String name) {
        if (null == childList) {
            return null;
        }
        List<XmlNodeModel> nameChildList = new ArrayList<>();
        for (XmlNodeModel node : childList) {
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

    public void addCdata(String cdata) {
        if (null == cdataList) {
            cdataList = new ArrayList<>();
        }
        cdataList.add(cdata);
    }

    public void addText(String text) {
        if (null == textList) {
            textList = new ArrayList<>();
        }
        textList.add(text);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, String> attr) {
        this.attr = attr;
    }

    public List<XmlNodeModel> getChildList() {
        return childList;
    }

    public void setChildList(List<XmlNodeModel> childList) {
        this.childList = childList;
    }

    public List<String> getCdataList() {
        return cdataList;
    }

    public void setCdataList(List<String> cdataList) {
        this.cdataList = cdataList;
    }

    public List<String> getTextList() {
        return textList;
    }

    public void setTextList(List<String> textList) {
        this.textList = textList;
    }

}
