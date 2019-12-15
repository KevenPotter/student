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
    /*手机号码验证*/
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    /*邮箱号码验证*/
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    /*用户昵称验证*/
    public static final String REGEX_NICKNAME = "^([\u4e00-\u9fa5\\w]+|[\\w]+)$";

    /**
     * @param username 用户名
     * @return 返回判断值
     * @date 2019-12-15 00:36:33
     * @description
     */
    public static boolean isStudentNo(String username) {
        return Pattern.matches(REGEX_STUDENT_NO, username);
    }

    /**
     * @param username 用户名
     * @return 返回判断值
     * @date 2019-12-15 00:47:52
     * @description
     */
    public static boolean isStudentName(String username) {
        return false;
    }

    /**
     * @param username 用户名
     * @return 返回判断值
     * @date 2019-12-15 00:37:21
     * @description
     */
    public static boolean isEmail(String username) {
        return Pattern.matches(REGEX_EMAIL, username);
    }

    /**
     * @param username 用户名
     * @return 返回判断值
     * @date 2019-12-15 00:38:56
     * @description
     */
    public static boolean isMobile(String username) {
        return Pattern.matches(REGEX_MOBILE, username);
    }

    /**
     * @param username 用户名
     * @return 返回判断值
     * @date 2019-12-15 00:41:01
     * @description
     */
    public static boolean isNickname(String username) {
        return Pattern.matches(REGEX_NICKNAME, username);
    }

    public static void main(String[] args) {
        System.out.println(isStudentNo("201719070801010301"));
        System.out.println(isMobile("134733572221"));
        System.out.println(isEmail("11q.@acom"));
        System.out.println(isNickname("的11q_acom"));
        System.out.println(isNickname("11q_acom"));
    }
}
