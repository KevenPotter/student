package com.kevenpotter.student.utils;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-22 11:27:19
 * @description 字符串工具类
 */
public class StringUtils {

    /**
     * @param value 待检查的字符串
     * @return 返回一个真假值
     * @author KevenPotter
     * @date 2019-11-22 11:28:11
     * @description 对字符串进行判空操作
     */
    public static boolean isEmpty(String value) {
        if (value == null || "".equals(value.trim()) || "null".equals(value) || "undefined".equals(value)) {
            return true;
        }
        return false;
    }
}