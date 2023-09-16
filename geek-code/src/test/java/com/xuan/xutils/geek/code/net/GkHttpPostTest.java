package com.xuan.xutils.geek.code.net;

/**
 * @author xuan
 * @since 2023/9/12
 */
public class GkHttpPostTest {

    public static void main(String[] args) {
        GkHttpPost httpPost = new GkHttpPost();
        String content = httpPost.post("https://news.163.com/", null);
        System.out.println(content);
    }

}
