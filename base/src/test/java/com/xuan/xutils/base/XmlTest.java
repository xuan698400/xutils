package com.xuan.xutils.base;

import com.xuan.xutils.base.xml.XmlReader;
import com.xuan.xutils.base.xml.XmlWriter;
import com.xuan.xutils.base.xml.impl.DefaultXmlReader;
import com.xuan.xutils.base.xml.impl.DefaultXmlWriter;
import com.xuan.xutils.base.xml.model.Xml;
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
        Xml xml = doReadXml();
        System.out.println(xmlWriter.write(xml));
    }

    private Xml doReadXml() {
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<bpm code=\"bpm.ktvExample\" name=\"中文哦\">"
            + "  <autoTask a=\"111\" b=\"222\"/>"
            + "  <decition aa=\"111aaa\" ba=\"222aaa\"><![CDATA[中文]]><![CDATA[中文]]>343</decition>"
            + "  <decition1 ddd=\"vvv\">noCData</decition1>"
            + "</bpm>";

        Xml xml = xmlReader.read(str, "utf-8");
        System.out.println(xml.getSchema().getEncoding());
        System.out.println(xml.getSchema().getVersion());
        System.out.println("=======================");
        System.out.println(xml.getRootNode().getName());
        System.out.println(xml.getRootNode().getAttribute("name"));
        System.out.println(xml.getRootNode().getAttributeMap().size());
        System.out.println(xml.getRootNode().getChildList().size());
        System.out.println(xml.getRootNode().getFistChild("decition").getCdataList());
        System.out.println(xml.getRootNode().getFistChild("decition").getTextList());
        System.out.println(xml.getRootNode().getFistChild("decition1").getCdataList());
        System.out.println(xml.getRootNode().getFistChild("decition1").getTextList());
        return xml;
    }

}
