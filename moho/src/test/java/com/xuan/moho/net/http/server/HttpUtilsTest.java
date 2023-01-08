package com.xuan.moho.net.http.server;

import java.io.File;

import com.xuan.moho.net.http.client.HttpDownloadListener;
import com.xuan.moho.net.http.client.HttpResponse;
import com.xuan.moho.net.http.client.HttpUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class HttpUtilsTest {

    @Test
    public void testGet() {
        HttpResponse response = HttpUtils.get("http://www.baidu.com", null);
        System.out.println(response);
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("OK", response.getReasonPhrase());
        Assert.assertTrue(response.getResultStr().contains("百度一下，你就知道"));
    }

    @Test
    public void testGetDownload() {
        String saveFileName = "/Users/xuan/Downloads/HttpUtilsTest_testGetDownload_file";

        //删除文件
        File file = new File(saveFileName);
        file.delete();

        //下载
        HttpResponse response = HttpUtils.getDownload("http://www.baidu.com", null,
            saveFileName, new HttpDownloadListener() {
                @Override
                public void callBack(long count, long current, boolean isFinish) {
                    System.out.println(String.format("count[%s], current[%s], isFinish[%s]", count, current, isFinish));
                }
            });
        System.out.println(response);
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("OK", response.getReasonPhrase());

        //判断文件是否存在
        file = new File(saveFileName);
        Assert.assertTrue(file.exists());
    }

}
