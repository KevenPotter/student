package com.kevenpotter.student.dao;

import com.kevenpotter.student.domain.dto.SystemUserDto;
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
     * @param systemUserDto 后台用户数据传输类
     * @return 根据[后台用户数据传输类]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2019-12-11 21:18:43
     * @description 根据[后台用户数据传输类]返回[后台用户实体类]
     */
    SystemUserEntity getSystemUser(@Param("systemUserDto") SystemUserDto systemUserDto);

    /**
     * @param systemUserDto 后台用户数据传输类
     * @author KevenPotter
     * @date 2019-12-19 10:19:50
     * @description 插入一条新的[后台用户实体]
     */
    void addSystemUser(@Param("systemUserDto") SystemUserDto systemUserDto);

    /**
     * @param id 主键ID
     * @return 根据[主键ID]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2019-12-19 11:17:33
     * @description 根据[主键ID]返回[后台用户实体类]
     */
    SystemUserEntity getSystemUserById(@Param("id") Long id);

    /**
     * @param userNickname 用户昵称
     * @return 根据[用户昵称]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2019-12-18 15:00:48
     * @description 根据[用户昵称]返回[后台用户实体类]
     */
    SystemUserEntity getSystemUserByNickname(@Param("userNickname") String userNickname);

    /**
     * @param userEmail 用户邮箱
     * @return 根据[用户邮箱]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2019-12-18 15:03:32
     * @description 根据[用户邮箱]返回[后台用户实体类]
     */
    SystemUserEntity getSystemUserByEmail(@Param("userEmail") String userEmail);

    /**
     * @param userMobile 用户手机
     * @return 根据[用户手机]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2019-12-18 15:24:56
     * @description 根据[用户手机]返回[后台用户实体类]
     */
    SystemUserEntity getSystemUserByMobile(@Param("userMobile") Long userMobile);
}

