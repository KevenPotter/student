package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 移除用户密码与用户盐值的后台用户数据传输类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2021-01-11 09:57:06
 */
@Data
@Accessors(chain = true)
public class SystemUserWithoutImportantInformationDto {

    /*主键ID*/
    private Long id;
    /*用户编号(学号、教工号)*/
    private String userId;
    /*手机号码*/
    private Long userMobile;
    /*邮箱号码*/
    private String userEmail;
    /*用户昵称*/
    private String userNickName;
    /*用户状态(0.不启用 1.启用)*/
    private Integer userStatus;
}
