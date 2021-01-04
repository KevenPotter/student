package com.kevenpotter.student.dao;

import com.github.pagehelper.Page;
import com.kevenpotter.student.domain.dto.SystemRoleDto;
import com.kevenpotter.student.domain.entity.SystemRoleEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-11 21:14:26
 * @description 后台角色持久层类
 */
@Repository
@Mapper
public interface SystemRoleDao {

    /**
     * 根据给定的参数进行[系统角色实体]列表的查询
     *
     * @param roleName   角色名称
     * @param roleStatus 角色状态
     * @return 返回根据给定的参数进行[系统角色实体]列表的查询
     * @author KevenPotter
     * @date 2021-01-04 09:22:01
     */
    @Select("<script> " +
            "SELECT * FROM system_role sr " +
            "<where> " +
            "<if test='roleName != null'> " +
            "AND sr.role_name LIKE CONCAT('%',#{roleName},'%') " +
            "</if>" +
            "<if test='roleStatus != null'> " +
            "AND sr.role_status = #{roleStatus}  " +
            "</if> " +
            "</where>" +
            "</script>")
    Page<SystemRoleEntity> getRoles(@Param("roleName") String roleName, @Param("roleStatus") Integer roleStatus);

    /**
     * 根据[角色名称]查询[系统角色实体]
     *
     * @param roleName 角色名称
     * @return 返回根据[角色名称]查询[系统角色实体]
     * @author KevenPotter
     * @date 2021-01-04 09:28:46
     */
    @Select("SELECT * FROM system_role sr WHERE sr.role_name = #{roleName}")
    SystemRoleEntity getRoleByRoleName(@Param("roleName") String roleName);

    /**
     * 根据[角色编号]查询[系统角色实体]
     *
     * @param id 角色编号
     * @return 返回一个[系统角色实体]
     * @author KevenPotter
     * @date 2021-01-04 09:30:11
     */
    @Select("SELECT * FROM system_role sr WHERE sr.id = #{id}")
    SystemRoleEntity getSystemRoleById(@Param("id") Long id);

    /**
     * 插入一条新的[系统角色实体]
     *
     * @param systemRoleDto 系统角色数据传输类
     * @author KevenPotter
     * @date 2021-01-04 09:28:51
     */
    @Insert("INSERT INTO `student`.`system_role` (`role_name`, `role_status`, `role_create_time`, `role_update_time`) VALUES (#{systemRoleDto.roleName}, #{systemRoleDto.roleStatus}, NOW(), NOW());")
    @Options(useGeneratedKeys = true, keyProperty = "systemRoleDto.id", keyColumn = "id")
    void addSystemRole(@Param("systemRoleDto") SystemRoleDto systemRoleDto);

    /**
     * 更新[系统角色实体]
     *
     * @param systemRoleDto 系统角色数据传输类
     * @author KevenPotter
     * @date 2021-01-04 09:41:19
     */
    void updateSystemRole(@Param("systemRoleDto") SystemRoleDto systemRoleDto);
}

