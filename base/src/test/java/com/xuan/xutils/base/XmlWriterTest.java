package com.xuan.xutils.base;

import com.xuan.xutils.base.xml.XmlWriter;
import com.xuan.xutils.base.xml.impl.DefaultXmlWriter;
import com.xuan.xutils.base.xml.model.Xml;
import com.xuan.xutils.base.xml.model.XmlNode;
import com.xuan.xutils.base.xml.model.XmlSchema;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author xuan
 * @since 2023/8/26
 */
public class XmlWriterTest {

    private XmlWriter xmlWriter;

    @Before
    public void init() {
        xmlWriter = new DefaultXmlWriter();
    }

    @Test
    public void testWrite() throws Exception {

        Xml xml = new Xml();

        XmlSchema xmlSchema = new XmlSchema();
        xmlSchema.setEncoding("UTF-8");
        xmlSchema.setVersion("1.0");
        xml.setSchema(xmlSchema);

        XmlNode rootNode = XmlNode.of("bpm");
        rootNode.addAttribute("name", "111");
        rootNode.addCdata("cdata测试");
        rootNode.addText("text测试");
        xml.setRootNode(rootNode);

        XmlNode childNode = XmlNode.of("subNode", rootNode);
        childNode.addText("subText");

        String xmlContent = xmlWriter.write(xml);
        Assert.assertEquals(xmlContent, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<bpm name=\"111\"><![CDATA[cdata测试]]>text测试\n"
            + "  <subNode>subText</subNode>\n"
            + "</bpm>");
        System.out.println(xmlContent);
    }
}
