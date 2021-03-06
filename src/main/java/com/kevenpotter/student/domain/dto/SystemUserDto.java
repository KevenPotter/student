package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 系统用户数据传输类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-11 16:10:26
 */
@Data
@Accessors(chain = true)
public class SystemUserDto {

    /*主键ID*/
    private Long id;
    /*用户编号(学号、教工号)*/
    private Long userId;
    /*手机号码*/
    private Long userMobile;
    /*邮箱号码*/
    private String userEmail;
    /*用户昵称*/
    private String userNickName;
    /*用户密码*/
    private String userPassword;
    /*用户状态(0.不启用 1.启用)*/
    private Integer userStatus;
    /*加密盐值*/
    private String salt;
    /*用户创建时间*/
    private LocalDateTime userCreateTime;
    /*用户更新时间*/
    private LocalDateTime userUpdateTime;
}
