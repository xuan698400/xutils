package com.xuan.moho.xml;

import com.xuan.moho.xml.model.XmlModel;

/**
 * @author xuan
 * @since 2023/1/9
 */
public interface XmlReader {

    /**
     * 读取xml文件到模型
     *
     * @param xml     xml文件
     * @param charset 编码
     * @return xml模型
     */
    XmlModel read(String xml, String charset);
}
