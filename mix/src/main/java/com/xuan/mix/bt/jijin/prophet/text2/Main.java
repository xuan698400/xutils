package com.xuan.mix.bt.jijin.prophet.text2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.xuan.mix.bt.jijin.prophet.Data;
import com.xuan.mix.bt.jijin.prophet.TxtUtils;
import com.xuan.mix.utils.MathUtils;

/**
 * @author xuan
 * @since 2022/7/23
 */
public class Main {

    private static List<DayLine> dayLineList = new ArrayList<>();

    private static double startPoint = 0;

    public static void main(String[] args) {
        List<Data> dataList = TxtUtils.readData2("005918_2");
        Collections.reverse(dataList);

        //
        int i = 0;
        for (Data data : dataList) {
            DayLine dl;
            double diff;
            if (i == 0) {
                //初始化仓位
                dl = DayLine.init(data);
                startPoint = data.price;
                diff = 0;
            } else {
                DayLine preDl = dayLineList.get(dayLineList.size() - 1);
                diff = preDl.getCurrentPrice() - startPoint;

                if (diff > 0.0200D) {
                    double sellOutNum = div(5000D, data.price);
                    dl = DayLine.sellOut(preDl, data, sellOutNum);
                    startPoint = data.price;
                } else if (diff < -0.0200D) {
                    dl = DayLine.buyIn(preDl, data, 5000D);
                    startPoint = data.price;
                } else {
                    dl = DayLine.noOp(preDl, data);
                }
            }
            dayLineList.add(dl);
            System.out.println(dl + "diff:" + diff);
            i++;
        }
        //
        for (DayLine dl : dayLineList) {
            //System.out.println(dl);
        }
    }

    public static double div(double v1, double v2) {
        return MathUtils.div(v1, v2, 4);
    }

    public static double add(double v1, double v2) {
        return MathUtils.add(v1, v2);
    }

}
