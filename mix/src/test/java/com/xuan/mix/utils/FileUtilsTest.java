package com.xuan.mix.utils;

import java.io.File;
import java.util.List;

import org.junit.Test;

/**
 * @author xuan
 * @since 2019/12/30
 */
public class FileUtilsTest {

    @Test
    public void testGetFileListByPath() {
        List<File> fileList = FileUtils.getFileListByPath("/Users/xuan/Desktop/xuan");
        for (File f : fileList) {
            System.out.println(f.getName());
        }
    }

}
