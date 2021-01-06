package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 系统角色-菜单-模块-权限数据传输类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2021-01-06 10:29:27
 */
@Data
@Accessors(chain = true)
public class SystemRoleMenuModulePermissionDto {

    /*主键ID*/
    private Long id;
    /*角色编号*/
    private Long roleId;
    /*菜单编号*/
    private Long menuId;
    /*模块编号*/
    private Long moduleId;
    /*权限功能*/
    private String permissions;
    /*权限状态(0.不启用 1.启用)*/
    private Integer status;
    /*创建时间*/
    private LocalDateTime createTime;
    /*更新时间*/
    private LocalDateTime updateTime;
}
