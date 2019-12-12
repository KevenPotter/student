package com.kevenpotter.student.dao;

import com.kevenpotter.student.domain.entity.SystemUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
     * @param userId   用户编号(学号、教工号)
     * @param userName 用户名称
     * @return 根据[用户编号]或[用户名称]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2019-12-11 21:18:43
     * @description 根据[用户编号]或[用户名称]返回[后台用户实体类]
     */
    SystemUserEntity getSystemUser(@Param("userId") Long userId, @Param("userName") String userName);
}

