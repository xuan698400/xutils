package com.xuan.moho.net.http.client;

/**
 * @author xuan
 * @since 2022/3/28
 */
public class ArticleTest {

    public static void main(String[] args) {
        HttpResponse response = HttpUtils.get("https://www.runoob.com/design-pattern/factory-pattern.html", null);
        System.out.println(response.getResultStr());

        String content = response.getResultStr();
        content = content.split("工厂模式")[1].split("其他相关文章")[0];
        System.out.println(content);
    }

}
