package com.kevenpotter.student.utils;

import java.util.regex.Pattern;

/**
 * @author KevenPotter
 * @company https://github.com/KevenPotter/student
 * @date 2019-12-15 00:24:50
 * @description 用户认证服务层类
 */
public class AccountVerification {

    /*学生编号验证*/
    public static final String REGEX_STUDENT_NO = "(^\\d{18}$)";
    /*学生姓名验证*/
    public static final String REGEX_STUDENT_NAME = "[\\u4E00-\\u9FA5]+";
    /*手机号码验证*/
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[5-9]))\\d{8}$";
    /*邮箱号码验证*/
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    /*用户昵称验证*/
    public static final String REGEX_NICKNAME = "_";

    /**
     * @param username 用户名
     * @return 返回判断值
     * @date 2019-12-15 00:36:33
     * @description 判断用户名是否是[学生编号]形式
     */
    public static boolean isStudentNo(String username) {
        return Pattern.matches(REGEX_STUDENT_NO, username);
    }

    /**
     * @param username 用户名
     * @return 返回判断值
     * @date 2019-12-15 00:47:52
     * @description 判断用户名是否是[学生姓名]形式
     */
    public static boolean isStudentName(String username) {
        return Pattern.matches(REGEX_STUDENT_NAME, username);
    }

    /**
     * @param username 用户名
     * @return 返回判断值
     * @date 2019-12-15 00:37:21
     * @description 判断用户名是否是[邮箱号码]形式
     */
    public static boolean isEmail(String username) {
        return Pattern.matches(REGEX_EMAIL, username);
    }

    /**
     * @param username 用户名
     * @return 返回判断值
     * @date 2019-12-15 00:38:56
     * @description 判断用户名是否是[手机号码]形式
     */
    public static boolean isMobile(String username) {
        return Pattern.matches(REGEX_MOBILE, username);
    }

    /**
     * @param username 用户名
     * @return 返回判断值
     * @date 2019-12-15 00:41:01
     * @description 判断用户名是否是[昵称]形式
     */
    public static boolean isNickname(String username) {
        return username.contains(REGEX_NICKNAME);
    }
}
