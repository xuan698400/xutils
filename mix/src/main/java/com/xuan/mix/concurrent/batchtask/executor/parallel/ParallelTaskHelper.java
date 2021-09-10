package com.xuan.mix.concurrent.batchtask.executor.parallel;

import java.util.ArrayList;
import java.util.List;

/**
 * 主要用来切割任务
 *
 * 如果总数据足够多，那么需要进行数据拆分，根据配置，拆分成多个子数据集合
 * 例如：totalList.size()=20，subSize=3
 * 那么：会拆分子数据集合：3，3，3，3，3，3，2一共7个子数据集合
 *
 * @author xuan
 * @since 2021/9/10
 */
public class ParallelTaskHelper {

    public static <E> List<List<E>> split(List<E> totalList, int subSize) {
        List<List<E>> splitList = new ArrayList<>();

        if (null == totalList || totalList.isEmpty()) {
            return splitList;
        }

        List<E> subList = new ArrayList<>();
        splitList.add(subList);
        int index = 0;
        for (E e : totalList) {
            if (index == subSize) {
                subList = new ArrayList<>();
                splitList.add(subList);
                index = 0;
            }
            subList.add(e);
            index++;
        }
        return splitList;
    }

}
