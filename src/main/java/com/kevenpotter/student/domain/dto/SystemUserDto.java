package com.kevenpotter.student.domain.dto;

import lombok.Data;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-11 16:10:26
 * @description 后台用户数据传输类
 */
@Data
public class SystemUserDto {

    /*用户编号(学号、教工号)*/
    private Long userId;
    /*用户名称*/
    private String userName;
    /*用户昵称*/
    private String userNickname;
    /*用户邮箱*/
    private String email;
    /*用户手机*/
    private Long mobile;
    /*用户密码*/
    private String userPassword;
}
