package com.xuan.mix.http;

/**
 * 下载文件时的进度监听
 *
 * @author xuan
 * @date 2019/5/19
 */
public interface HttpDownloadListener {
    /**
     * 处理结果时回调
     *
     * @param count    总量字节
     * @param current  当前处理字节
     * @param isFinish 是否处理完成
     */
    void callBack(long count, long current, boolean isFinish);
}
