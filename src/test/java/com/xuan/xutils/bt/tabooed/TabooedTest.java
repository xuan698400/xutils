package com.xuan.xutils.bt.tabooed;

import org.junit.Test;

import java.util.List;

/**
 * 铭感词测试
 * <p>
 * Created by xuan on 17/8/8.
 */
public class TabooedTest {

    private final static String TEXT = "我是一个学习java的小朋友,听说这个里面会有很多铭感词.我是中国人也不能说么,哎我太失望了.就这样吧.这也行的啊";

    @Test
    public void testGetTabooedWords() {
        /**
         * 测试下来好像性能不是很好郁闷,可能跟铭感词的关键字多少有关系
         */
        List<String> tabooedList = TabooedUtils.getTabooedWords(TEXT);
        System.out.println("++++++++++tabooedList:" + tabooedList);
    }

}
