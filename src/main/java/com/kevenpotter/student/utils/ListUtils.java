package com.kevenpotter.student.utils;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 计算两集合之间的差集，这个差集是由l1-l2得到的
     *
     * @param l1  集合1
     * @param l2  集合2
     * @param <T> 需要返回集合的泛型
     * @return 返回两集合之间的差集
     * @author KevenPotter
     * @date 2021-01-08 09:11:56
     */
    public static <T> List<T> differenceSet(List<T> l1, List<T> l2) {
        return l1.stream().filter(e -> !l2.contains(e)).collect(Collectors.toList());
    }

    /**
     * 计算两集合之间的交集
     *
     * @param l1  集合1
     * @param l2  集合2
     * @param <T> 需要返回集合的泛型
     * @return 返回两集合之间的交集
     * @author KevenPotter
     * @date 2021-01-08 09:57:37
     */
    public static <T> List<T> intersectionSet(List<T> l1, List<T> l2) {
        return l1.stream().filter(l2::contains).collect(Collectors.toList());
    }

    /**
     * 对集合进行去重
     *
     * @param l   集合
     * @param <T> 需要返回集合的泛型
     * @return 返回去重后的集合
     * @author KevenPotter
     * @date 2021-01-08 12:07:36
     */
    public static <T> List<T> distinct(List<T> l) {
        return l.stream().distinct().collect(Collectors.toList());
    }
}
