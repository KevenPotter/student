package com.kevenpotter.student.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 后台用户实体类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-11 16:02:17
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserEntity implements Serializable {

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
    /*加密盐值*/
    private String salt;
}
