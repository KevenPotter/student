package com.kevenpotter.student.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统角色-菜单-模块-权限实体类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-12-28 13:31:26
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SystemRoleMenuModulePermissionEntity implements Serializable {

    /*主键ID*/
    private Long id;
    /*角色编号*/
    private Long roleId;
    /*菜单编号*/
    private Long menuId;
    /*模块编号*/
    private Long moduleId;
    /*权限功能*/
    private Long permission;
}
