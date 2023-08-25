package com.xuan.moho.net.http.client;

/**
 * @author xuan
 * @since 2022/3/28
 */
public class Main {

    public static void main(String[] args) {
        //testGet();
        //testGetDowload2();
        HttpResponse response = HttpUtils.get("https://workbench2.dw.alibaba-inc.com/workbench/cwf/node/code?projectId=22289&env=prod&tenantId=1&nodeId=184817219", null);
        System.out.println(response.getResultStr());
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
            "/Users/xuan/Desktop/baidu222222.html", new HttpDownloadListener() {
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

    public static void testGetDowload2() {
        int m = 202110;
        for (int i = 5851; i < 6900; i++) {
            final int ii = i;

            HttpResponse httpResponse = HttpUtils.getDownload(
                "https://downsc.chinaz.net/Files/DownLoad/moban/" + m + "/moban" + ii + ".rar", null,
                "/Users/xuan/Desktop/code/github/html_market/" + m + "/moban" + ii + ".rar",
                new HttpDownloadListener() {
                    @Override
                    public void callBack(long count, long current, boolean isFinish) {
                        //System.out.println(
                        //    "下载[" + ii + "]++++++++++进度:count[" + count + "]current[" + current + "]isFinish["
                        //        + isFinish
                        //        + "]");
                    }
                });

            if (httpResponse.isStatusOk()) {
                System.out.println("下载成功[" + ii + "]");
            } else {
                System.out.println("下载失败[" + ii + "]");
            }
        }
    }

}
