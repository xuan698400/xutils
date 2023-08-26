package com.xuan.xutils.design.filter;

/**
 * @author xuan
 * @since 2023/3/6
 */
public class JapaneseSayHelloFilter extends BaseSayHelloFilter {

    @Override
    protected Response doHandle(Request request) {
        return Response.of(String.format("你好！%s 我是日本人", request.getName()));

    }

    @Override
    protected String supportLang() {
        return "japanese";
    }
    
}
