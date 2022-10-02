package com.xuan.mix.net.http;

import com.xuan.mix.net.miniclient.MiniClientDownloadListener;
import com.xuan.mix.net.miniclient.MiniClientResponse;
import com.xuan.mix.net.miniclient.MiniClientFacade;
import org.junit.Test;

/**
 * Http模块测试
 * <p>
 * Created by xuan on 17/8/1.
 */
public class HttpTest {

    @Test
    public void testGet() {
        MiniClientResponse httpResponse = MiniClientFacade.get("http://www.baidu.com", null);
        if (httpResponse.isStatusOk()) {
            System.out.println("请求成功");
        }
        System.out.println("++++++++++httpResponse:" + httpResponse);
    }

    @Test
    public void testGetDowload() {
        MiniClientResponse httpResponse = MiniClientFacade.getDownload("http://www.baidu.com", null, "/Users/xuan/Desktop/baidu.html", new MiniClientDownloadListener() {
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
