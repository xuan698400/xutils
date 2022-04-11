package com.xuan.mix.bt.jijin.prophet.test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import com.xuan.mix.bt.jijin.prophet.Data;
import com.xuan.mix.bt.jijin.prophet.TxtUtils;

/**
 * @author xuan
 * @since 2022/4/10
 */
public class Chart1 {
    public static void main(String[] args) throws Exception {
        List<Data> list = TxtUtils.readData("005918");
        Collections.reverse(list);

        StringBuilder date = new StringBuilder();
        StringBuilder price = new StringBuilder();
        for (Data data : list) {
            if (date.length() > 0) {
                date.append(",");
                price.append(",");
            }
            date.append("'" + data.date.replace("2022-", "").replace("-", ".") + "'");
            price.append(BigDecimal.valueOf(data.price).subtract(BigDecimal.valueOf(1L)));
        }
        System.out.println(date.toString());
        System.out.println("");
        System.out.println(price.toString());
    }

}
