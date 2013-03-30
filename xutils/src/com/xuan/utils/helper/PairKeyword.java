package com.xuan.utils.helper;

/**
 * 关键字对，用于列表排序
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午9:48:29 $
 */
public class PairKeyword {

    private final String name;
    private final int index;

    public PairKeyword(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

}
