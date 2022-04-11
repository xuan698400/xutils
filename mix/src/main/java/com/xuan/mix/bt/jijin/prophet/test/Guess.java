package com.xuan.mix.bt.jijin.prophet.test;

import java.util.Collections;
import java.util.List;

import com.xuan.mix.bt.jijin.prophet.Data;
import com.xuan.mix.bt.jijin.prophet.TxtUtils;

/**
 * @author xuan
 * @since 2022/3/13
 */
public class Guess {
    private static final boolean DETAIL_LOG = true;

    public static String guessIfUp(Data pre, Data prepre) {
        return "1";
    }

    public static void doGuess() throws Exception {
        List<Data> list = TxtUtils.readData("005918");
        Collections.reverse(list);

        Double total = 0D;
        Double hit = 0D;

        for (int i = 0, n = list.size(); i < n; i++) {
            if (i == 0 || i == 1) {
                continue;
            }

            Data current = list.get(i);
            Data pre = list.get(i - 1);
            Data prepre = list.get(i - 2);

            if ("0".equals(guessIfUp(pre, prepre))) {
                continue;
            }

            total++;
            if ("1".equals(guessIfUp(pre, prepre)) && current.price > pre.price) {
                hit++;
            }
            if ("0".equals(guessIfUp(pre, prepre)) && current.price < pre.price) {
                hit++;
            }

            if (DETAIL_LOG) {
                System.out.println("date:" + current.date);
                System.out.println("guess:" + guessIfUp(pre, prepre));
                System.out.println("current_price:" + current.price);
                System.out.println("pre_price:" + pre.price);
                System.out.println("diff:" + (current.price - pre.price));
                System.out.println("============================================");
            }
        }

        System.out.println("total:" + total);
        System.out.println("hit_count:" + hit);
        System.out.println("hit_percent:" + (hit / total));
    }

}
