package com.kevenpotter.student.utils;

import java.util.List;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-29 09:00:00
 * @description 集合工具类
 */
public class ListUtils {

    /**
     * @param list 待检查的集合
     * @return 返回一个真假值
     * @author KevenPotter
     * @date 2019-11-29 09:01:22
     * @description 对集合进行判空操作
     */
    public static boolean isEmpty(List list) {
        return null == list || list.size() <= 0;
    }
}
