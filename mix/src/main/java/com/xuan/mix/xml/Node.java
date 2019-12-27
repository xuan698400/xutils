package com.xuan.mix.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Xml节点模型
 *
 * @author xuan
 * @since 2019/10/22
 */
public class Node {

    /**
     * 节点类型. 例如<input>...</input>中的input值
     */
    private String type;
    /**
     * 节点包裹的值. 例如：<input><![CDATA[value]]></input>中的value
     */
    private String value;
    /**
     * value值是否是CDATA，默认是，输出时会被<![CDATA[value]]>包裹
     */
    private boolean isValueData = true;
    /**
     * 节点属性. <input name="abc"><![CDATA[value]]></input>中的name="abc"
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
    private List<Node> childList;

    public Node(String type) {
        this.type = type;
    }

    /**
     * 构建一个节点，并挂载到指定父节点下
     *
     * @param type   节点类型
     * @param parent 父节点
     * @return 新建节点
     */
    public static Node buildNode(String type, Node parent) {
        Node node = new Node(type);
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
    public void addChild(Node node) {
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
    public Node getFistChild() {
        if (null == childList || childList.size() <= 0) {
            return null;
        }

        return childList.get(0);
    }

    /**
     * 获取指定节点类型子节点中的第一个节点
     *
     * @param type 指定节点类型
     * @return 符合节点类型的第一个子节点
     */
    public Node getFistChild(String type) {
        List<Node> typeNodeList = getChildList(type);
        if (null == typeNodeList || typeNodeList.size() <= 0) {
            return null;
        }

        return typeNodeList.get(0);
    }

    /**
     * 获取指定节点类型的所有子节点
     *
     * @param type 指定节点类型
     * @return 符合节点类型的子节点列表
     */
    public List<Node> getChildList(String type) {
        if (null == childList) {
            return null;
        }
        List<Node> typeChildList = new ArrayList<>();
        for (Node node : childList) {
            if (Objects.equals(node.getType(), type)) {
                typeChildList.add(node);
            }
        }
        return typeChildList;
    }

    /**
     * 删除清理所有子节点
     */
    public void clearChild() {
        if (null == childList) {
            return;
        }
        childList.clear();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, String> attr) {
        this.attr = attr;
    }

    public List<Node> getChildList() {
        return childList;
    }

    public void setChildList(List<Node> childList) {
        this.childList = childList;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isValueData() {
        return isValueData;
    }

    public void setValueData(boolean valueData) {
        isValueData = valueData;
    }

}
