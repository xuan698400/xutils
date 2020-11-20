package com.xuan.mix.bt.tracker;

/**
 * 快捷的构建一个节点
 *
 * @author xuan
 * @since 2020/11/20
 */
class TkPointBuilder {

    /**
     * 构建第一个点
     *
     * @param name   名称
     * @param remark 备注
     * @return TkPoint
     */
    public static TkPoint buildStart(String name, String remark) {
        TkPoint tkPoint = new TkPoint(name);
        tkPoint.setInterval(0L);
        tkPoint.setRemark(remark);
        return tkPoint;
    }

    /**
     * 构建中间的点
     *
     * @param pre    上一个节点对象
     * @param name   名称
     * @param remark 备注
     * @return TkPoint
     */
    public static TkPoint buildOn(TkPoint pre, String name, String remark) {
        TkPoint tkPoint = new TkPoint(name);
        tkPoint.setRemark(remark);

        if (null == pre || null == pre.getTimestamp() || null == tkPoint.getTimestamp()) {
            return tkPoint;
        }
        tkPoint.setInterval(tkPoint.getTimestamp() - pre.getTimestamp());
        return tkPoint;
    }

}
