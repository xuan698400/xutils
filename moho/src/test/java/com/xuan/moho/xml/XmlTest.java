package com.xuan.moho.xml;

import com.xuan.moho.xml.core.DefaultXmlReader;
import com.xuan.moho.xml.core.DefaultXmlWriter;
import com.xuan.moho.xml.model.XmlModel;
import org.junit.Before;
import org.junit.Test;

/**
 * @author xuan
 * @since 2023/1/9
 */
public class XmlTest {

    private XmlReader xmlReader;

    private XmlWriter xmlWriter;

    @Before
    public void init() {
        xmlReader = new DefaultXmlReader();
        xmlWriter = new DefaultXmlWriter();
    }

    @Test
    public void testReader() {
        doReadXml();
    }

    @Test
    public void testWrite() {
        XmlModel xmlModel = doReadXml();
        System.out.println(xmlWriter.write(xmlModel));
    }

    private XmlModel doReadXml() {
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<bpm code=\"bpm.ktvExample\" name=\"中文哦\">"
            + "  <autoTask a=\"111\" b=\"222\"/>"
            + "  <decition aa=\"111aaa\" ba=\"222aaa\"><![CDATA[中文]]><![CDATA[中文]]>343</decition>"
            + "  <decition1 ddd=\"vvv\">noCData</decition1>"
            + "</bpm>";

        XmlModel xmlModel = xmlReader.read(str, "utf-8");
        System.out.println(xmlModel.getSchema().getEncoding());
        System.out.println(xmlModel.getSchema().getVersion());
        System.out.println("=======================");
        System.out.println(xmlModel.getRootNode().getName());
        System.out.println(xmlModel.getRootNode().getAttr("name"));
        System.out.println(xmlModel.getRootNode().getAttr().size());
        System.out.println(xmlModel.getRootNode().getChildList().size());
        System.out.println(xmlModel.getRootNode().getFistChild("decition").getCdataList());
        System.out.println(xmlModel.getRootNode().getFistChild("decition").getTextList());
        System.out.println(xmlModel.getRootNode().getFistChild("decition1").getCdataList());
        System.out.println(xmlModel.getRootNode().getFistChild("decition1").getTextList());
        return xmlModel;
    }

}
