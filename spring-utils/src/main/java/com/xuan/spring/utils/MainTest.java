package com.xuan.spring.utils;

import net.coobird.thumbnailator.Thumbnails;

/**
 * @author xuan
 * @since 2022/5/30
 */
public class MainTest {
    public static void main(String[] args) throws Exception {
        Thumbnails.of("/Users/xuan/Downloads/one_press.jpg").size(700, 990).toFile(
            "/Users/xuan/Downloads/one_press_done.jpg");
    }

}
