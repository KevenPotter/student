package com.kevenpotter.student.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-11 16:21:02
 * @description 后台用户-角色实体类
 */
@Data
public class SystemUserRoleEntity implements Serializable {

    /*主键ID*/
    private Long id;
    /*用户编号(学号、教工号)*/
    private Long userId;
    /*角色编号*/
    private Long roleId;
}
