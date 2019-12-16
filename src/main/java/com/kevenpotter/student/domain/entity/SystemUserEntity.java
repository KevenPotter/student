package com.kevenpotter.student.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-11 16:02:17
 * @description 后台用户实体类
 */
@Data
public class SystemUserEntity implements Serializable {

    /*主键ID*/
    private Long id;
    /*用户编号(学号、教工号)*/
    private Long userId;
    /*用户名称*/
    private String userName;
    /*手机号码*/
    private Long userMobile;
    /*邮箱号码*/
    private String userEmail;
    /*用户昵称*/
    private String userNickName;
    /*用户密码*/
    private String userPassword;
}
