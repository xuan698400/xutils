package com.xuan.xutils.geek.code.net;

/**
 * @author xuan
 * @since 2023/9/12
 */
public class GkHttpGetTest {

    public static void main(String[] args) {
        GkHttpGet httpGet = new GkHttpGet();
        String content = httpGet.get("https://news.163.com/");
        System.out.println(content);
    }

}
