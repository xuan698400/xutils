package com.xuan.mix.net.http;

import com.xuan.mix.an.httpclient.HttpResponse;
import com.xuan.mix.an.httpclient.HttpDownloadListener;
import com.xuan.mix.an.httpclient.HttpUtils;
import org.junit.Test;

/**
 * Http模块测试
 * <p>
 * Created by xuan on 17/8/1.
 */
public class HttpTest {

    @Test
    public void testGet() {
        HttpResponse httpResponse = HttpUtils.get("http://www.baidu.com", null);
        if (httpResponse.isStatusOk()) {
            System.out.println("请求成功");
        }
        System.out.println("++++++++++httpResponse:" + httpResponse);
    }

    @Test
    public void testGetDowload() {
        HttpResponse httpResponse = HttpUtils.getDownload("http://www.baidu.com", null, "/Users/xuan/Desktop/baidu.html", new HttpDownloadListener() {
            @Override
            public void callBack(long count, long current, boolean isFinish) {
                System.out.println("++++++++++进度:count[" + count + "]current[" + current + "]isFinish[" + isFinish + "]");
            }
        });

        if (httpResponse.isStatusOk()) {
            System.out.println("下载成功");
        }
        System.out.println("++++++++++httpResponse:" + httpResponse);
    }

}
