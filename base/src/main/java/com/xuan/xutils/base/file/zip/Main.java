package com.xuan.xutils.base.file.zip;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuan
 * @since 2022/10/18
 */
public class Main {

    public static void main(String[] args) throws Exception {
        testUnzip();
    }

    private static void testZip() throws Exception {
        File f1 = new File("/Users/xuan/Desktop/111.sh");
        File f2 = new File("/Users/xuan/Desktop/222");

        List<File> fileList = new ArrayList<>();
        fileList.add(f1);
        fileList.add(f2);

        ZipUtils.zip(fileList, new File("/Users/xuan/Desktop/111222.zip"));
    }

    private static void testUnzip() throws Exception {
        ZipUtils.unzip(new File("/Users/xuan/Desktop/111222.zip"), "/Users/xuan/Desktop/unzip111222/");
    }
}
