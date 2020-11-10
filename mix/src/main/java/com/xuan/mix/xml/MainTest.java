package com.xuan.mix.xml;

import com.xuan.mix.xml.model.XmlModel;

/**
 * @author xuan
 * @since 2020/11/10
 */
public class MainTest {

    public static void main(String[] args) {
        writeTest(readTest());
    }

    private static XmlModel readTest() {
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<bpm code=\"bpm.ktvExample\" name=\"中文哦\"> \n"
            + "  <autoTask a=\"111\" b=\"222\"/>\n"
            + "  <decition aa=\"111aaa\" ba=\"222aaa\"><![CDATA[中文]]><![CDATA[中文]]>343</decition>\n"
            + "  <decition1 ddd=\"vvv\">noCData</decition1>\n"
            + "</bpm>";

        XmlModel xmlModel = XmlReader.read(str, "utf-8");
        System.out.println(xmlModel.getSchemaModel().getEncoding());
        System.out.println(xmlModel.getSchemaModel().getVersion());
        System.out.println("=======================");
        System.out.println(xmlModel.getNodeModel().getName());
        System.out.println(xmlModel.getNodeModel().getAttr("name"));
        System.out.println(xmlModel.getNodeModel().getAttr().size());
        System.out.println(xmlModel.getNodeModel().getChildList().size());
        System.out.println(xmlModel.getNodeModel().getFistChild("decition").getCdataList());
        System.out.println(xmlModel.getNodeModel().getFistChild("decition").getTextList());
        System.out.println(xmlModel.getNodeModel().getFistChild("decition1").getCdataList());
        System.out.println(xmlModel.getNodeModel().getFistChild("decition1").getTextList());
        return xmlModel;
    }

    private static void writeTest(XmlModel xmlModel) {
        System.out.println(XmlWriter.write(xmlModel));
    }

}
