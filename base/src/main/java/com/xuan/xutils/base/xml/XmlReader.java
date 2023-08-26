package com.xuan.xutils.base.xml;

import com.xuan.xutils.base.xml.model.Xml;

/**
 * XML文本转模型
 *
 * @author xuan
 * @since 2023/1/9
 */
public interface XmlReader {

    /**
     * 读取XML文件到模型
     *
     * @param xmlContent XML文件
     * @param charset    编码
     * @return XML模型
     */
    Xml read(String xmlContent, String charset);
}
