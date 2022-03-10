package com.xuan.mix.bt.creep;

import com.xuan.mix.bt.creep.utils.HttpUtil;

/**
 * @author xuan
 * @since 2020/9/3
 */
public class Starter {

    public static void main(String[] args) throws Exception {

        Long t = System.currentTimeMillis();
        t = t + 500;

        //String url =
        //    "http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery183007340129993178213_1630487785085&fundCode"
        //        + "=005918&pageIndex=2&pageSize=20&startDate=&endDate=&_=" + t;

        String url
            = "https://work.alibaba-inc.com/work/xservice/sns/suggestion/suggestionAt"
            + ".jsonp?_input_charset=utf-8&key=%E5%BE%90%E5%B7%A5&size=10&callback=exceed_1646123292121";

        System.out.println(url);

        String result = HttpUtil.get(url);
        System.out.println(result);
    }

}
