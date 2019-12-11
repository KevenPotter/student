package com.kevenpotter.student.domain.dto;

import lombok.Data;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-11 16:10:26
 * @description 后台数据传输类
 */
@Data
public class SystemUserDto {

    /*主键ID*/
    private Long id;
    /*用户编号(学号、教工号)*/
    private Long userId;
    /*用户名称*/
    private String userName;
    /*用户密码*/
    private String userPassword;
}
