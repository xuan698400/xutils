package com.xuan.xutils.base.xml;

import com.xuan.xutils.base.xml.model.Xml;

/**
 * XML模型转文本
 *
 * @author xuan
 * @since 2023/1/9
 */
public interface XmlWriter {

    /**
     * 写入XML默认到文本
     *
     * @param xml XML模型
     * @return XML文本
     */
    String write(Xml xml);
}
