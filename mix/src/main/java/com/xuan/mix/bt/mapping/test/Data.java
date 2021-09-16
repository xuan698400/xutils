package com.xuan.mix.bt.mapping.test;

import java.util.List;

/**
 * @author xuan
 * @since 2021/9/15
 */
public class Data {

    private Integer num;

    private int sNum;

    private DataItem dataItem;

    private List<DataItem> dataItemList;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public int getsNum() {
        return sNum;
    }

    public void setsNum(int sNum) {
        this.sNum = sNum;
    }

    public DataItem getDataItem() {
        return dataItem;
    }

    public void setDataItem(DataItem dataItem) {
        this.dataItem = dataItem;
    }

    public List<DataItem> getDataItemList() {
        return dataItemList;
    }

    public void setDataItemList(List<DataItem> dataItemList) {
        this.dataItemList = dataItemList;
    }

}
