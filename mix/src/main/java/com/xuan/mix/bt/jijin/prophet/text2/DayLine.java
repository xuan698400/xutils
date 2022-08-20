package com.xuan.mix.bt.jijin.prophet.text2;

import com.xuan.mix.bt.jijin.prophet.Data;
import com.xuan.mix.utils.MathUtils;

/**
 * @author xuan
 * @since 2022/7/23
 */
public class DayLine {
    /**
     * 当前日期
     */
    private String date;
    /**
     * 持仓总金额
     */
    private double totalYuan;
    /**
     * 外部总金额
     */
    private double outTotalYuan;
    /**
     * 平均单价
     */
    private double avgPrice;
    /**
     * 份额
     */
    private double num;
    /**
     * 当前单价
     */
    private double currentPrice;
    /**
     * 持仓收益
     */
    private double profit;
    /**
     * 持仓收益百分比
     */
    private double profitPercent;
    /**
     * 操作提示
     */
    private String msg;

    @Override
    public String toString() {
        return String.format(
            "date:%s, totalYuan:%s, outTotalYuan:%s, num:%s, avgPrice:%s, currentPrice:%s, profit:%s, "
                + "profitPercent:%s, msg:%s \n",
            date,
            totalYuan, outTotalYuan, num, avgPrice, currentPrice, profit, profitPercent, msg);
    }

    public static DayLine init(Data currentData) {
        DayLine dayLine = new DayLine();
        dayLine.setDate(currentData.date);
        dayLine.setCurrentPrice(currentData.price);
        dayLine.setTotalYuan(10000D);
        dayLine.setOutTotalYuan(10000D);
        dayLine.setNum(div(dayLine.getTotalYuan(), currentData.price));
        dayLine.setAvgPrice(div(dayLine.getTotalYuan(), dayLine.getNum()));
        dayLine.setProfit(mul(sub(dayLine.getCurrentPrice(), dayLine.getAvgPrice()), dayLine.getNum()));
        dayLine.setProfitPercent(mul(div(dayLine.getProfit(), dayLine.getTotalYuan()), 100D));
        dayLine.setMsg("初始化仓位");
        return dayLine;
    }

    public static DayLine noOp(DayLine preDl, Data currentData) {
        DayLine dayLine = new DayLine();
        dayLine.setDate(currentData.date);
        dayLine.setCurrentPrice(currentData.price);
        dayLine.setTotalYuan(preDl.getTotalYuan());
        dayLine.setOutTotalYuan(preDl.getOutTotalYuan());
        dayLine.setNum(preDl.getNum());
        dayLine.setAvgPrice(div(dayLine.getTotalYuan(), dayLine.getNum()));
        dayLine.setProfit(mul(sub(dayLine.getCurrentPrice(), dayLine.getAvgPrice()), dayLine.getNum()));
        dayLine.setProfitPercent(mul(div(dayLine.getProfit(), dayLine.getTotalYuan()), 100D));
        dayLine.setMsg("不操作");
        return dayLine;
    }

    public static DayLine buyIn(DayLine preDl, Data currentData, double buyInYuan) {
        DayLine dayLine = new DayLine();
        dayLine.setDate(currentData.date);
        dayLine.setCurrentPrice(currentData.price);

        double restOutTotalYuan = sub(preDl.getOutTotalYuan(), buyInYuan);
        if (restOutTotalYuan >= 0) {
            dayLine.setTotalYuan(add(preDl.getTotalYuan(), buyInYuan));
            dayLine.setOutTotalYuan(restOutTotalYuan);
            double buyInNum = div(buyInYuan, currentData.price);
            dayLine.setNum(add(preDl.getNum(), buyInNum));
            dayLine.setMsg("买入操作");
        } else {
            dayLine.setTotalYuan(preDl.getTotalYuan());
            dayLine.setOutTotalYuan(preDl.getOutTotalYuan());
            dayLine.setNum(preDl.getNum());
            dayLine.setMsg("买入操作，但是没钱买入");
        }

        dayLine.setAvgPrice(div(dayLine.getTotalYuan(), dayLine.getNum()));
        dayLine.setProfit(mul(sub(dayLine.getCurrentPrice(), dayLine.getAvgPrice()), dayLine.getNum()));
        dayLine.setProfitPercent(mul(div(dayLine.getProfit(), dayLine.getTotalYuan()), 100D));
        return dayLine;
    }

    public static DayLine sellOut(DayLine preDl, Data currentData, double sellOutNum) {
        DayLine dayLine = new DayLine();
        dayLine.setDate(currentData.date);
        dayLine.setCurrentPrice(currentData.price);

        double restNum = sub(preDl.getNum(), sellOutNum);
        if (restNum >= 0) {
            double sellOutYuan = mul(sellOutNum, currentData.price);
            dayLine.setTotalYuan(sub(preDl.getTotalYuan(), sellOutYuan));
            dayLine.setOutTotalYuan(add(preDl.getOutTotalYuan(), sellOutYuan));
            dayLine.setNum(restNum);
            dayLine.setMsg("卖出操作");
        } else {
            dayLine.setTotalYuan(preDl.getTotalYuan());
            dayLine.setOutTotalYuan(preDl.getOutTotalYuan());
            dayLine.setNum(preDl.getNum());
            dayLine.setMsg("卖出操作，但没有足够份额卖出");
        }

        dayLine.setAvgPrice(div(dayLine.getTotalYuan(), dayLine.getNum()));
        dayLine.setProfit(mul(sub(dayLine.getCurrentPrice(), dayLine.getAvgPrice()), dayLine.getNum()));
        dayLine.setProfitPercent(mul(div(dayLine.getProfit(), dayLine.getTotalYuan()), 100D));
        return dayLine;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalYuan() {
        return totalYuan;
    }

    public void setTotalYuan(double totalYuan) {
        this.totalYuan = totalYuan;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public static double div(double v1, double v2) {
        return MathUtils.div(v1, v2, 4);
    }

    public static double add(double v1, double v2) {
        return MathUtils.add(v1, v2);
    }

    public static double sub(double v1, double v2) {
        return MathUtils.sub(v1, v2);
    }

    public static double mul(double v1, double v2) {
        return MathUtils.mul(v1, v2);
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getProfitPercent() {
        return profitPercent;
    }

    public void setProfitPercent(double profitPercent) {
        this.profitPercent = profitPercent;
    }

    public double getOutTotalYuan() {
        return outTotalYuan;
    }

    public void setOutTotalYuan(double outTotalYuan) {
        this.outTotalYuan = outTotalYuan;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
