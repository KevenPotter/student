package com.kevenpotter.student.dao;

import com.github.pagehelper.Page;
import com.kevenpotter.student.domain.dto.SystemRoleMenuModulePermissionDto;
import com.kevenpotter.student.domain.entity.SystemRoleEntity;
import com.kevenpotter.student.domain.entity.SystemRoleMenuModulePermissionEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统角色-菜单-模块-权限持久层类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2021-01-06 10:22:48
 */
@Repository
@Mapper
public interface SystemRoleMenuModulePermissionDao {

    /**
     * 根据给定的参数进行[系统角色-菜单-模块-权限实体]列表的查询
     *
     * @param systemRoleMenuModulePermissionDto 系统角色-菜单-模块-权限数据传输类
     * @return 返回根据给定的参数进行[系统角色-菜单-模块-权限实体]列表的查询
     * @author KevenPotter
     * @date 2021-01-06 14:22:30
     */
    @Select("<script> " +
            "SELECT * FROM system_role_menu_module_permission srmmp " +
            "<where> " +
            "<if test='systemRoleMenuModulePermissionDto.id != null'> " +
            "AND srmmp.id = #{systemRoleMenuModulePermissionDto.id}  " +
            "</if> " +
            "<if test='systemRoleMenuModulePermissionDto.roleId != null'> " +
            "AND srmmp.role_id = #{systemRoleMenuModulePermissionDto.roleId}  " +
            "</if> " +
            "<if test='systemRoleMenuModulePermissionDto.menuId != null'> " +
            "AND srmmp.menu_id = #{systemRoleMenuModulePermissionDto.menuId}  " +
            "</if> " +
            "<if test='systemRoleMenuModulePermissionDto.moduleId != null'> " +
            "AND srmmp.module_id = #{systemRoleMenuModulePermissionDto.moduleId}  " +
            "</if> " +
            "<if test='systemRoleMenuModulePermissionDto.permissions != null'> " +
            "AND srmmp.permissions = #{systemRoleMenuModulePermissionDto.permissions}  " +
            "</if> " +
            "<if test='systemRoleMenuModulePermissionDto.status != null'> " +
            "AND srmmp.status = #{systemRoleMenuModulePermissionDto.status}  " +
            "</if> " +
            "</where>" +
            "</script>")
    Page<SystemRoleEntity> getRolesForPage(@Param("systemRoleMenuModulePermissionDto") SystemRoleMenuModulePermissionDto systemRoleMenuModulePermissionDto);

    /**
     * 根据给定的参数进行[系统角色-菜单-模块-权限实体]列表的查询
     *
     * @param systemRoleMenuModulePermissionDto 系统角色-菜单-模块-权限数据传输类
     * @return 返回根据给定的参数进行[系统角色-菜单-模块-权限实体]列表的查询
     * @author KevenPotter
     * @date 2021-01-06 14:15:08
     */
    @Select("<script> " +
            "SELECT * FROM system_role_menu_module_permission srmmp " +
            "<where> " +
            "<if test='systemRoleMenuModulePermissionDto.id != null'> " +
            "AND srmmp.id = #{systemRoleMenuModulePermissionDto.id}  " +
            "</if> " +
            "<if test='systemRoleMenuModulePermissionDto.roleId != null'> " +
            "AND srmmp.role_id = #{systemRoleMenuModulePermissionDto.roleId}  " +
            "</if> " +
            "<if test='systemRoleMenuModulePermissionDto.menuId != null'> " +
            "AND srmmp.menu_id = #{systemRoleMenuModulePermissionDto.menuId}  " +
            "</if> " +
            "<if test='systemRoleMenuModulePermissionDto.moduleId != null'> " +
            "AND srmmp.module_id = #{systemRoleMenuModulePermissionDto.moduleId}  " +
            "</if> " +
            "<if test='systemRoleMenuModulePermissionDto.permissions != null'> " +
            "AND srmmp.permissions = #{systemRoleMenuModulePermissionDto.permissions}  " +
            "</if> " +
            "<if test='systemRoleMenuModulePermissionDto.status != null'> " +
            "AND srmmp.status = #{systemRoleMenuModulePermissionDto.status}  " +
            "</if> " +
            "</where>" +
            "</script>")
    List<SystemRoleMenuModulePermissionEntity> getRolesForList(@Param("systemRoleMenuModulePermissionDto") SystemRoleMenuModulePermissionDto systemRoleMenuModulePermissionDto);

    /**
     * 根据[角色编号]查询[全部系统角色-菜单-模块-权限数据传输类]
     *
     * @param roleId 角色编号
     * @return 返回根据[角色编号]查询[全部系统角色-菜单-模块-权限数据传输类]
     * @author KevenPotter
     * @date 2021-01-06 11:28:09
     */
    @Select("SELECT * FROM system_role_menu_module_permission srmmp WHERE srmmp.role_id = #{roleId}")
    List<SystemRoleMenuModulePermissionDto> getSystemRoleMenuModulePermissionByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据[角色-菜单-模块-权限编号]查询[系统角色-菜单-模块-权限实体]
     *
     * @param id 角色-菜单-模块-权限编号
     * @return 返回一个[系统角色-菜单-模块-权限实体]
     * @author KevenPotter
     * @date 2021-01-06 14:10:15
     */
    @Select("SELECT * FROM system_role_menu_module_permission srmmp WHERE srmmp.id = #{id}")
    SystemRoleMenuModulePermissionEntity getSystemRoleMenuModulePermissionById(@Param("id") Long id);

    /**
     * 插入一条新的[系统角色-菜单-模块-权限实体]
     *
     * @param systemRoleMenuModulePermissionDto 系统角色-菜单-模块-权限数据传输类
     * @author KevenPotter
     * @date 2021-01-06 13:44:09
     */
    @Insert("INSERT INTO `student`.`system_role_menu_module_permission` (`role_id`, `menu_id`, `module_id`, `permissions`, `status`, `create_time`, `update_time`) VALUES (#{systemRoleMenuModulePermissionDto.roleId}, #{systemRoleMenuModulePermissionDto.menuId}, #{systemRoleMenuModulePermissionDto.moduleId}, #{systemRoleMenuModulePermissionDto.permissions}, #{systemRoleMenuModulePermissionDto.status}, NOW(), NOW());")
    @Options(useGeneratedKeys = true, keyProperty = "systemRoleMenuModulePermissionDto.id", keyColumn = "id")
    void addSystemRoleMenuModulePermission(@Param("systemRoleMenuModulePermissionDto") SystemRoleMenuModulePermissionDto systemRoleMenuModulePermissionDto);
}

