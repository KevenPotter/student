package com.kevenpotter.student.domain.dto;

import lombok.Data;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-11 16:18:10
 * @description 后台角色数据传输类
 */
@Data
public class SystemRoleDto {

    /*主键ID*/
    private Long id;
    /*角色编号*/
    private Long roleId;
    /*角色名称*/
    private String roleName;
}
