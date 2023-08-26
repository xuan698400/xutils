package com.xuan.xutils.base;

import java.nio.charset.Charset;

import com.xuan.xutils.base.utils.ResourceUtils;
import com.xuan.xutils.base.xml.XmlReader;
import com.xuan.xutils.base.xml.impl.DefaultXmlReader;
import com.xuan.xutils.base.xml.model.Xml;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author xuan
 * @since 2023/8/26
 */
public class XmlReaderTest {

    private XmlReader xmlReader;

    @Before
    public void init() {
        xmlReader = new DefaultXmlReader();
    }

    @Test
    public void testRead() throws Exception {

        String xmlContent = ResourceUtils.readToString("test.xml", Charset.forName("utf-8"));

        Xml xml = xmlReader.read(xmlContent, "utf-8");

        Assert.assertEquals(xml.getSchema().getEncoding(), "UTF-8");
        Assert.assertEquals(xml.getSchema().getVersion(), "1.0");
        Assert.assertEquals(xml.getRootNode().getName(), "bpm");
        Assert.assertEquals(xml.getRootNode().getAttribute("name"), "属性中文测试");
        Assert.assertEquals(xml.getRootNode().getFistChild("subNode1").getAttribute("a"), "111");
        Assert.assertEquals(xml.getRootNode().getFistChild("subNode1").getAttribute("b"), "222");

        Assert.assertEquals(xml.getRootNode().getFistChild("subNode2").getAttribute("aa"), "111aaa");
        Assert.assertEquals(xml.getRootNode().getFistChild("subNode2").getAttribute("bb"), "222aaa");
        Assert.assertEquals(xml.getRootNode().getFistChild("subNode2").getCdataList().get(0), "cdata中文测试");
        Assert.assertEquals(xml.getRootNode().getFistChild("subNode2").getCdataList().get(1), "cdata-english-test");
        Assert.assertEquals(xml.getRootNode().getFistChild("subNode2").getTextList().get(0), "文本Test");

        Assert.assertEquals(xml.getRootNode().getFistChild("subNode3").getAttribute("ddd"), "vvv");
        Assert.assertEquals(xml.getRootNode().getFistChild("subNode3").getTextList().get(0), "noCData");

        //这里看下输出内容，单侧是否通过还是以上面断言为准
        System.out.println(xml.getSchema().getEncoding());
        System.out.println(xml.getSchema().getVersion());
        System.out.println("=======================");
        System.out.println(xml.getRootNode().getName());
        System.out.println(xml.getRootNode().getAttribute("name"));
        System.out.println(xml.getRootNode().getAttributeMap().size());
        System.out.println(xml.getRootNode().getChildList().size());
        System.out.println(xml.getRootNode().getFistChild("subNode1").getCdataList());
        System.out.println(xml.getRootNode().getFistChild("subNode1").getTextList());
        System.out.println(xml.getRootNode().getFistChild("subNode2").getCdataList());
        System.out.println(xml.getRootNode().getFistChild("subNode2").getTextList());
        System.out.println(xml.getRootNode().getFistChild("subNode3").getCdataList());
        System.out.println(xml.getRootNode().getFistChild("subNode3").getTextList());
    }

}
