package com.kevenpotter.student.dao;

import com.kevenpotter.student.domain.dto.SystemUserRoleDto;
import com.kevenpotter.student.domain.entity.SystemUserRoleEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-11 21:16:01
 * @description 后台用户-角色持久层类
 */
@Repository
@Mapper
public interface SystemUserRoleDao {

    /**
     * 根据[用户编号]返回一个[系统用户-角色实体]
     *
     * @param userId 用户编号
     * @return 根据[用户编号]返回一个[系统用户-角色实体]
     * @author KevenPotter
     * @date 2021-01-11 15:31:26
     */
    @Select("SELECT * FROM system_user_role sur WHERE sur.system_user_id = #{userId}")
    SystemUserRoleEntity getSystemUserRoleByUserId(@Param("userId") String userId);

    /**
     * 根据[主键编号]返回一个[系统用户-角色实体]
     *
     * @param id 主键编号
     * @return 根据[主键编号]返回一个[系统用户-角色实体]
     * @author KevenPotter
     * @date 2021-01-11 16:23:25
     */
    @Select("SELECT * FROM system_user_role sur WHERE sur.id = #{id}")
    SystemUserRoleEntity getSystemUserRoleById(@Param("id") Long id);

    /**
     * 插入一条新的[系统用户-角色实体]
     *
     * @param systemUserRoleDto 系统用户角色数据传输类
     * @author KevenPotter
     * @date 2021-01-11 16:25:08
     */
    @Insert("INSERT INTO `student`.`system_user_role` (`system_user_id`, `system_role_id`, `user_role_status`, `user_role_create_time`, `user_role_update_time`) VALUES (#{systemUserRoleDto.systemUserId}, #{systemUserRoleDto.systemRoleId}, #{systemUserRoleDto.userRoleStatus}, NOW(), NOW());")
    @Options(useGeneratedKeys = true, keyProperty = "systemUserRoleDto.id", keyColumn = "id")
    void addUserRole(@Param("systemUserRoleDto") SystemUserRoleDto systemUserRoleDto);

    /**
     * 更新[系统用户-角色实体]
     *
     * @param systemUserRoleDto 系统用户角色数据传输类
     * @author KevenPotter
     * @date 2021-01-12 09:36:43
     */
    void updateSystemUserRole(@Param("systemUserRoleDto") SystemUserRoleDto systemUserRoleDto);
}

