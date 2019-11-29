package com.kevenpotter.student.utils;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-29 09:57:16
 * @description 数字工具类
 */
public class NumericUtils {

    /**
     * @param numeric 待检查的数字集合
     * @return 返回一个真假值
     * @author KevenPotter
     * @date 2019-11-29 10:11:25
     * @description 对数字集合进行判空操作
     */
    public static boolean isEmpty(Object... numeric) {
        if (null == numeric) return false;
        for (Object o : numeric) {
            if (null == o) return true;
        }
        return false;
    }


    /**
     * @param integers 待检查的Integer集合
     * @return 返回一个真假值
     * @author KevenPotter
     * @date 2019-11-29 09:59:14
     * @description 对Integer集合进行判空操作
     */
    public static boolean integerIsEmpty(Integer... integers) {
        for (Integer integerValue : integers) {
            if (null == integerValue) return true;
        }
        return false;
    }

    /**
     * @param longs 待检查的Long集合
     * @return 返回一个真假值
     * @author KevenPotter
     * @date 2019-11-29 10:00:54
     * @description 对Long集合进行判空操作
     */
    public static boolean longIsEmpty(Long... longs) {
        for (Long longValue : longs) {
            if (null == longValue) return true;
        }
        return false;
    }
}
