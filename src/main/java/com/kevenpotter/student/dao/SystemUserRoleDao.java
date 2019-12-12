package com.kevenpotter.student.dao;

import com.kevenpotter.student.domain.entity.SystemUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * @param userId 用户编号
     * @return 根据[用户编号]返回一个[后台用户-角色实体]
     * @author KevenPotter
     * @date 2019-12-11 22:41:28
     * @description 根据[用户编号]返回一个[后台用户-角色实体]
     */
    @Select("SELECT * FROM system_user_role sur WHERE sur.user_id = #{userId}")
    List<SystemUserRoleEntity> getSystemUserRoleBy(@Param("userId") Long userId);
}

