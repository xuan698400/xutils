package com.xuan.moho.xml;

import com.xuan.moho.xml.model.XmlModel;

/**
 * @author xuan
 * @since 2023/1/9
 */
public interface XmlReader {

    XmlModel read(String xml, String charset);
}
