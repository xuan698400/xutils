package com.xuan.mix.bt.jijin.prophet;

import java.util.Collections;
import java.util.List;

/**
 * @author xuan
 * @since 2022/3/13
 */
public class Main {

    private static final boolean DETAIL_LOG = false;

    public static void main(String[] args) throws Exception {
        List<Data> list = TxtUtils.readData("005918");
        Collections.reverse(list);

        Double total = 0D;
        Double hit = 0D;

        for (int i = 0, n = list.size(); i < n; i++) {
            if (i == 0) {
                continue;
            }

            Data current = list.get(i);
            Data pre = list.get(i - 1);
            total++;
            if (Guess.isUp() && current.price > pre.price) {
                hit++;
            }
            if (!Guess.isUp() && current.price < pre.price) {
                hit++;
            }

            if (DETAIL_LOG) {
                System.out.println("guess:" + Guess.isUp());
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
