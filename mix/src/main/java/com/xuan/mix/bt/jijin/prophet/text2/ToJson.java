package com.xuan.mix.bt.jijin.prophet.text2;

import java.util.Collections;
import java.util.List;

import com.alibaba.fastjson.JSON;

import com.xuan.mix.bt.jijin.prophet.Data;
import com.xuan.mix.bt.jijin.prophet.TxtUtils;

/**
 * @author xuan
 * @since 2022/8/26
 */
public class ToJson {
    public static void main(String[] args) {
        List<Data> dataList = TxtUtils.readData2("005918_3");
        Collections.reverse(dataList);

        for (Data data : dataList) {
            data.date = data.date.replaceAll("-", "").substring(4);
        }

        System.out.println(JSON.toJSONString(dataList));
    }

}
