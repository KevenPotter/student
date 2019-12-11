package com.kevenpotter.student.dao;

import com.kevenpotter.student.domain.entity.SystemUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-11 21:15:26
 * @description 后台用户持久层类
 */
@Repository
@Mapper
public interface SystemUserDao {

    /**
     * @param userId 用户编号(学号、教工号)
     * @return 根据[用户编号]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2019-12-11 21:18:43
     * @description 根据[用户编号]返回[后台用户实体类]
     */
    @Select("SELECT * FROM system_user su WHERE su.user_id = #{userId}")
    SystemUserEntity getSystemUserByUserId(@Param("userId") Long userId);

    /**
     * @param userName 用户名称
     * @return 根据[用户名称]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2019-12-11 21:20:15
     * @description 根据[用户名称]返回[后台用户实体类]
     */
    @Select("SELECT * FROM system_user su WHERE su.user_name = #{userName}")
    SystemUserEntity getSystemUserByUserName(@Param("userName") String userName);
}

