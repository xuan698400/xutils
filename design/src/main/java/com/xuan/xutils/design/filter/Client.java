package com.xuan.xutils.design.filter;

/**
 * @author xuan
 * @since 2023/3/6
 */
public class Client {

    public static void main(String[] args) {
        SayHelloFilterChain chain = new SayHelloFilterChain();
        SayHelloFilter head = chain.getHead();

        //
        Request request = new Request();
        request.setName("中国名");
        request.setLang("chinese");
        Response response = head.handle(request);
        System.out.println(response.getMessage());

        request.setName("英国名");
        request.setLang("english");
        response = head.handle(request);
        System.out.println(response.getMessage());

        request.setName("日本名");
        request.setLang("japanese");
        response = head.handle(request);
        System.out.println(response.getMessage());
    }

}
