package com.kevenpotter.student.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-11 16:16:54
 * @description 后台角色实体类
 */
@Data
public class SystemRoleEntity implements Serializable {

    /*主键ID*/
    private Long id;
    /*角色编号*/
    private Long roleId;
    /*角色名称*/
    private String roleName;
}
