package com.xuan.moho.xml;

import com.xuan.moho.xml.model.XmlModel;

/**
 * @author xuan
 * @since 2023/1/9
 */
public interface XmlWriter {

    /**
     * 根据xml写成xml文件
     *
     * @param xmlModel xml模型
     * @return xml文件
     */
    String write(XmlModel xmlModel);
}
