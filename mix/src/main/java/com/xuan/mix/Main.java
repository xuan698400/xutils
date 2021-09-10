package com.xuan.mix;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuan
 * @since 2021/9/8
 */
public class Main {

    public static void main(String[] args) throws Exception {
        //System.out.println(URLEncoder.encode("韩时烤肉•韩式拌饭(悠唐店)外卖订单", "UTF-8"));
        //System.out.println(URLDecoder.decode(
        //    "%E9%9F%A9%E6%97%B6%E7%83%A4%E8%82%89%E2%80%A2%E9%9F%A9%E5%BC%8F%E6%8B%8C%E9%A5%AD%28%E6%82%A0%E5%94%90"
        //        + "%E5%BA%97%29%E5%A4%96%E5%8D%96%E8%AE%A2%E5%8D%95",
        //    "UTF-8"));

        Map<String, Object> dd = new HashMap<>();
        dd.put("dd", null);
        dd = null;
        for (Map.Entry<String, Object> entry : dd.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

}
