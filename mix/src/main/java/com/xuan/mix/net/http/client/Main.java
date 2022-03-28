package com.xuan.mix.net.http.client;

/**
 * @author xuan
 * @since 2022/3/28
 */
public class Main {

    public static void main(String[] args) {
        testGet();
    }

    public static void testGet() {
        HttpResponse httpResponse = HttpUtils.get("http://www.baidu.com", null);
        if (httpResponse.isStatusOk()) {
            System.out.println("请求成功");
        }
        System.out.println("++++++++++httpResponse:" + httpResponse);
    }

    public static void testGetDowload() {
        HttpResponse httpResponse = HttpUtils.getDownload("http://www.baidu.com", null,
            "/Users/xuan/Desktop/baidu.html", new HttpDownloadListener() {
                @Override
                public void callBack(long count, long current, boolean isFinish) {
                    System.out.println(
                        "++++++++++进度:count[" + count + "]current[" + current + "]isFinish[" + isFinish + "]");
                }
            });

        if (httpResponse.isStatusOk()) {
            System.out.println("下载成功");
        }
        System.out.println("++++++++++httpResponse:" + httpResponse);
    }

}
