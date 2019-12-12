package com.kevenpotter.student.dao;

import com.kevenpotter.student.domain.entity.SystemRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
     * @param roleId 角色编号
     * @return 根据[角色编号]返回一个[后台角色实体]
     * @author KevenPotter
     * @date 2019-12-11 22:41:28
     * @description 根据[角色编号]返回一个[后台角色实体]
     */
    @Select("SELECT * FROM system_role sr WHERE sr.role_id = #{roleId}")
    SystemRoleEntity getSystemRoleByRoleId(@Param("roleId") Long roleId);
}

