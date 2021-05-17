package com.xuan.mix.bt.jijin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuan
 * @since 2021/4/23
 */
public class Main {

    private static List<String> dataList = new ArrayList<>();
    private static List<Integer> valueList = new ArrayList<>();
    private static Map<String, List<Integer>> maMap = new HashMap<>();

    private static void initValue() {
        dataList.add("2021-05-07");
        dataList.add("2021-05-06");
        dataList.add("2021-04-30");
        dataList.add("2021-04-29");
        dataList.add("2021-04-28");
        dataList.add("2021-04-27");
        dataList.add("2021-04-26");
        dataList.add("2021-04-23");
        dataList.add("2021-04-22");
        dataList.add("2021-04-21");
        dataList.add("2021-04-20");
        dataList.add("2021-04-19");
        dataList.add("2021-04-16");
        dataList.add("2021-04-15");
        dataList.add("2021-04-14");
        dataList.add("2021-04-13");
        dataList.add("2021-04-12");
        dataList.add("2021-04-09");
        dataList.add("2021-04-08");
        dataList.add("2021-04-07");

        valueList.add(13897);
        valueList.add(14068);
        valueList.add(14233);
        valueList.add(14340);
        valueList.add(14220);
        valueList.add(14144);
        valueList.add(14107);
        valueList.add(14257);
        valueList.add(14133);
        valueList.add(14148);
        valueList.add(14107);
        valueList.add(14116);
        valueList.add(13799);
        valueList.add(13755);
        valueList.add(13837);
        valueList.add(13731);
        valueList.add(13752);
        valueList.add(13981);
        valueList.add(14183);
        valueList.add(14159);
    }

    private static void initMa(Integer interval) {
        //ma5
        List<Integer> maList = new ArrayList<>();
        for (int i = 0, n = valueList.size(); i < n; i++) {
            int maTatol = 0;
            int count = 0;
            for (int j = 0; j < interval; j++) {
                int index = i - j;
                if (index >= 0) {
                    maTatol += valueList.get(index);
                    count++;
                }
            }
            maList.add(maTatol / count);
        }
        maMap.put(String.valueOf(interval), maList);
    }

    public static void main(String[] args) {
        initValue();
        initMa(5);
        initMa(10);
        initMa(20);
        initMa(60);

        System.out.println(valueList);
        maMap.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });

    }

}
