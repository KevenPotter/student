package com.kevenpotter.student.utils;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-29 09:00:00
 * @description 集合工具类
 */
public class ListUtils {

    /**
     * 对集合进行判空操作
     *
     * @param list 待检查的集合
     * @return 返回一个真假值
     * @author KevenPotter
     * @date 2019-11-29 09:01:22
     */
    public static boolean isEmpty(List list) {
        return null == list || list.size() <= 0;
    }

    /**
     * 对集合的内容进行比对
     *
     * @param l1 对比的集合1
     * @param l2 对比的集合2
     * @return 返回集合比对结果
     * @author KevenPotter
     * @date 2021-01-07 10:31:48
     */
    public static boolean isEqual(List l1, List l2) {
        return CollectionUtils.isEqualCollection(l1, l2);
    }
}
